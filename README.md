# TemplateInjector for Vaadin 7

TemplateInjector is a layout component that supports layout based on a given HTML fragement (similar to Vaadin CustomLayout).
Every HTML element within the HTML fragment that is marked with an *id* tag can be used to assign a Vaadin component to that specific element.
 
See *templater-demo/.../TemplaterUI* for a typical use-case.

## Setup Maven multi-module project

The project is setup as a multi-module Maven project:

* templater-root
  * templater-demo (depends on templater)
  * templater 


## Compile and run

* Invoke Maven target `clean install` on *templater*.
* Invoke Maven target `clean install` on *templater-demo*.
* Invoke Maven target `jetty:run` on *templater-demo* to startup the Jetty server.

Application will be available under `localhost:8080`.
