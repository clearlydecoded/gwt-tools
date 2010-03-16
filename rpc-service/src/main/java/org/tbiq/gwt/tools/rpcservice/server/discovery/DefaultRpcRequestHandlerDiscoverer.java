/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package org.tbiq.gwt.tools.rpcservice.server.discovery;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.tbiq.gwt.tools.rpcservice.browser.RpcRequest;
import org.tbiq.gwt.tools.rpcservice.browser.RpcResponse;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceException;
import org.tbiq.gwt.tools.rpcservice.server.RpcRequestHandler;

/**
 * DefaultRpcRequestHandlerDiscoverer class is the default implementation of the
 * {@link RpcRequestHandlerDiscoverer} interface. It looks for classes in the specified
 * packages which are annotated with {@link RpcHandler} annotation as well as implements
 * {@link RpcRequestHandler} interface and loads them using their default (no-args)
 * constructor.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class DefaultRpcRequestHandlerDiscoverer
  implements RpcRequestHandlerDiscoverer
{
  /** Logger for this class. */
  private static Logger logger = Logger
    .getLogger(DefaultRpcRequestHandlerDiscoverer.class);

  /** List of package strings to look through to find RPC handlers in. */
  private List<String> packageNameList;

  /**
   * Constructor.
   * 
   * @param packageNames Array of 1 or more package name specifications for this
   *          discoverer to look through to find classes annotated with RpcHandler
   *          annotation.
   */
  public DefaultRpcRequestHandlerDiscoverer(String... packageNames)
  {
    // Check if packages are null
    if (packageNames == null)
    {
      String message = "No packages provided to search through for RPC";
      message += " request handlers.";
      logger.error(message);
      throw new RpcServiceException(message);
    }

    // Create a list of package names & check that no provided package is null or empty
    packageNameList = new ArrayList<String>();
    for (String packageName : packageNames)
    {
      if ((packageName == null) || packageName.trim().equals(""))
      {
        String message = "One of the provided package names is either null or empty.";
        logger.error(message);
        throw new RpcServiceException(message);
      }

      packageNameList.add(packageName.trim());
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<? extends RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> discoverHandlers()
  {
    // Find all RPC handler classes
    List<Class> rpcHandlerClasses = new ArrayList<Class>();
    for (String packageName : packageNameList)
    {
      try
      {
        rpcHandlerClasses.addAll(findRpcHandlerClasses(packageName));
      }
      catch (Exception exception)
      {
        // Log and rethrow exception
        logger.error(exception.getMessage());
        throw new RpcServiceException(exception);
      }
    }

    // Instantiate each RPC handler class
    List<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>> rpcHandlers;
    rpcHandlers = new ArrayList<RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>>();
    for (Class rpcHandlerClass : rpcHandlerClasses)
    {
      RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse> rpcHandler;
      try
      {
        // Instantiate RPC handler and add it to the collection
        rpcHandler = (RpcRequestHandler<? extends RpcRequest<? extends RpcResponse>, ? extends RpcResponse>) rpcHandlerClass
          .newInstance();
        rpcHandlers.add(rpcHandler);
      }
      catch (Exception exception)
      {
        // Log and rethrow
        logger.error(exception.getMessage(), exception);
        throw new RpcServiceException(exception);
      }
    }

    return rpcHandlers;
  }

  /**
   * This method looks through the provided <code>packageName</code> and any sub-packages
   * for all classes annotated with {@link RpcHandler} annotation. It returns a list of
   * those classes or an empty list if there are none.
   * 
   * @param packageName Name of the package to look through for RPC handler classes.
   * @return List of all RPC handler classes within the provided <code>packageName</code>.
   *         If no classes found, returns an empty list.
   * @throws IOException If anything goes wrong with accessing the files inside the
   *           <code>packageName</code> directory.
   * @throws ClassNotFoundException If anything goes wrong with loading found classes.
   */
  @SuppressWarnings("unchecked")
  public List<? extends Class> findRpcHandlerClasses(String packageName)
    throws IOException, ClassNotFoundException
  {
    // Retrieve the context classloader
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    // Check if classloader retrieval is successful
    if (classLoader == null)
    {
      String message = "Error retrieving current thread's context classloader.";
      logger.error(message);
      throw new RpcServiceException(message);
    }

    // Pull out all resources from the package directory
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> directories = new ArrayList<File>();
    while (resources.hasMoreElements())
    {
      URL resource = resources.nextElement();
      String fileName = resource.getFile();
      String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
      directories.add(new File(fileNameDecoded));
    }

    // Find RPC handler classes in every resource pointed to by the package directory
    List<Class> rpcHandlerClasses = new ArrayList<Class>();
    for (File directory : directories)
    {
      rpcHandlerClasses.addAll(findRpcHandlerClasses(directory, packageName));
    }

    return rpcHandlerClasses;
  }

  /**
   * Searches through the <code>directory</code> and finds all RPC handler classes
   * annotated with RpcHandler annotation as well as implementing the
   * {@link RpcRequestHandler} interface. Returns a list of classes or an empty list.
   * 
   * @param directory Base directory.
   * @param packageName Package name for classes found inside the base directory.
   * @return RPC handler classes contained with the base <code>directory</code> and any
   *         sub-directory of the base <code>directory</code>. If no classes found,
   *         returns an empty list.
   * @throws ClassNotFoundException If anything goes wrong with loading found classes.
   */
  @SuppressWarnings("unchecked")
  public List<? extends Class> findRpcHandlerClasses(File directory, String packageName)
    throws ClassNotFoundException
  {
    List<Class> rpcHandlerClasses = new ArrayList<Class>();

    // If directory doesn't exist, no classes can be found in it, return empty list
    if (!directory.exists())
    {
      return rpcHandlerClasses;
    }

    // Go through each file in this directory
    File[] files = directory.listFiles();
    for (File file : files)
    {
      String fileName = file.getName();

      // Check if this is a sub-directory
      if (file.isDirectory())
      {
        // Check that directory doesn't contain a '.'
        if (fileName.contains("."))
        {
          String message = "One of the subdirectories contains a '.', can't parse further";
          logger.error(message);
          throw new RpcServiceException(message);
        }

        // Find all RPC classes in this sub-directory
        rpcHandlerClasses
          .addAll(findRpcHandlerClasses(file, packageName + "." + fileName));
      }
      else if (fileName.endsWith(".class") && !fileName.contains("$"))
      {
        Class foundClass;
        try
        {
          foundClass = Class.forName(packageName + '.'
                                     + fileName.substring(0, fileName.length() - 6));
        }
        catch (ExceptionInInitializerError e)
        {
          // Special case for not initializing classes that depend on some context to
          // initialize them, i.e., Spring context which might inject other dependencies
          // into these classes. Therefore, the initialize flag is false.
          foundClass = Class.forName(packageName + '.'
                                       + fileName.substring(0, fileName.length() - 6),
                                     false,
                                     Thread.currentThread().getContextClassLoader());
        }

        // Add to RPC handler classes if annotated with RpcHandler annotation
        if (foundClass
          .isAnnotationPresent(org.tbiq.gwt.tools.rpcservice.server.discovery.RpcHandler.class))
        {
          // Make sure this class implements RpcRequestHandler interface
          Class[] implementedInterfaces = foundClass.getInterfaces();
          for (Class interfaceClass : implementedInterfaces)
          {
            if (interfaceClass.equals(RpcRequestHandler.class))
            {
              // Add found RPC handler class to the list
              rpcHandlerClasses.add(foundClass);
              break;
            }
          }
        }
      }
    }

    return rpcHandlerClasses;
  }
}
