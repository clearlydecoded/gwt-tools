# gwt-tools
Place Service to abstract browser history management, Easy to use GWT RPC framework to help with RPC calls and setup, MVP interfaces.

## This project contains:

**1) Place service to abstract history management**

**2) RPC Service to help with RPC calls and setup**

**3) MVP (Model View Presenter) interfaces**

**Example apps extending the Contacts app that Google provided as an MVC example to include:**
* Original Contacts app that Google provided, but mavenized
* Same as above plus using the new place-service
* Same as above plus using the rpc-service and using the presenter interfaces
* Same as above plus using Spring Framework enabled autodiscovery of RPC handlers

**NOTE:** Since GWT 2.1 release where they introduced the MVP framework, most of the MVP part of this project is not relevant *if* you are using GWT 2.1. However, if you are still using GWT 2.0, GWT 1.7, or GWT 1.6, all of the functionality of this framework are still very much applicable and useful.

Take a look at the different version of the Contacts applications to see how to use the framework. It's very easy.
