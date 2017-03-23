scalatest-springboot
--------------------

build 
-----

```
mvn clean install
```

use as deps
------------

```xml
    <dependency>
      <groupId>org.scalatest.springboot</groupId>
      <artifactId>scalatest-springboot</artifactId>
      <version>1.0</version>
    </dependency>
```

TODO add tests

```scala
@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class InventoryEndpointIntegrationSpecs extends FunSpec with SpringTestContextManager {

   @Autowired val mockMvc: MockMvc = null

   describe("Inventory Endpoint") {
      it("accepts json request and responds 200 on success"){
        val json = """{}"""
        
        val response =
                mockMvc.perform(MockMvcRequestBuilders.post("/ingest").content(json)).andDo(MockMvcResultHandlers.print())
        
              response.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().json(
                  """
                    |{
                    |  "eventId": "very-uniq-id",
                    |  "responseCode": "VLDN_FAIL",
                    |  "responseMessage": "[\"object has missing required properties ([\\\"EventBody\\\"])\"]"
                    |}
                  """.stripMargin))
                  
      }
   }
}
```