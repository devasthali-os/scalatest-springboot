scalatest-springboot
--------------------

build 
-----

```
mvn clean install
```

TODO add tests

```
@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class PurchaseOrderEndpointIntegrationSpecs extends FunSpec with SpringTestContextManager {
   describe("") {
      it(""){
        //
      }
   }
}
```

use as deps
------------

```
    <dependency>
      <groupId>org.scalatest.springboot</groupId>
      <artifactId>scalatest-springboot</artifactId>
      <version>1.0</version>
    </dependency>
```

