Coherence Event Processing : MapTrigger Feature

MapTrigger is one of the most important features of Oracle Coherence to provide a highly customized cache management system. It represents a functional agent that allows to validate, reject or modify mutating operations against an underlying map. Also, they can prevent invalid transactions, enforce security, provide event logging and auditing, and gather statistics on data modifications.

For example, we have code that is working with a NamedCache, and we want to change an entry’s behavior or contents before the entry is inserted into the map. This change can be made without modifying all the existing code by enabling a map trigger.

There are two ways to add Map Triggers feature to application :

1) Programmatic-based : The MapTriggerListener is a special purpose com.tangosol.util.MapListener implementation that is used to register a MapTrigger with a corresponding NamedCache.
2) Configuration-based : The class-factory mechanism can be used in the coherence-cache-config.xml configuration file as the following :
```html
<distributed-scheme>
...
   <listener>
      <class-scheme>
         <class-factory-name>package.MyFactory</class-factory-name>
         <method-name>createTriggerListener</method-name>
         <init-params>
            <init-param>
               <param-type>string</param-type>
               <param-value>{cache-name}</param-value>
            </init-param>
         </init-params>
      </class-scheme>
   </listener>
</distributed-scheme>
```
In the following sample application, MapTrigger feature is enabled programmatically by using MapTriggerListener. A new DATA4J cluster is created and User bean is distributed by user-map NamedCache object.

Used Technologies : JDK 1.7.0_40, Spring 3.2.4, Coherence 3.7.1 and Maven 3.0.4