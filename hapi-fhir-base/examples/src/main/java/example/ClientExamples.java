package example;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.client.IRestfulClientFactory;
import ca.uhn.fhir.rest.client.api.IBasicClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.server.EncodingEnum;

public class ClientExamples {

   public interface IPatientClient extends IBasicClient {
      // nothing yet
   }

   @SuppressWarnings("unused")
   public void createProxy() {
      // START SNIPPET: proxy
      FhirContext ctx = new FhirContext();
      ctx.getRestfulClientFactory().setProxy("example.com", 8888);

      IGenericClient genericClient = ctx.newRestfulGenericClient("http://localhost:9999/fhir");
      // END SNIPPET: proxy
   }

   @SuppressWarnings("unused")
   public void createSecurity() {
      // START SNIPPET: security
      // Create a context and get the client factory so it can be configured
      FhirContext ctx = new FhirContext();
      IRestfulClientFactory clientFactory = ctx.getRestfulClientFactory();

      // Create an HTTP basic auth interceptor
      String username = "foobar";
      String password = "boobear";
      BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(username, password);

      // Register the interceptor with your client (either style)
      IPatientClient annotationClient = ctx.newRestfulClient(IPatientClient.class, "http://localhost:9999/fhir");
      annotationClient.registerInterceptor(authInterceptor);

      IGenericClient genericClient = ctx.newRestfulGenericClient("http://localhost:9999/fhir");
      annotationClient.registerInterceptor(authInterceptor);
      // END SNIPPET: security
   }

   @SuppressWarnings("unused")
   public void createLogging() {
      {
         // START SNIPPET: logging
         // Create a context and get the client factory so it can be configured
         FhirContext ctx = new FhirContext();
         IRestfulClientFactory clientFactory = ctx.getRestfulClientFactory();

         // Create a logging interceptor
         LoggingInterceptor loggingInterceptor = new LoggingInterceptor();

         // Optionally you may configure the interceptor (by default only
         // summary info is logged)
         loggingInterceptor.setLogRequestSummary(true);
         loggingInterceptor.setLogRequestBody(true);

         // Register the interceptor with your client (either style)
         IPatientClient annotationClient = ctx.newRestfulClient(IPatientClient.class, "http://localhost:9999/fhir");
         annotationClient.registerInterceptor(loggingInterceptor);

         IGenericClient genericClient = ctx.newRestfulGenericClient("http://localhost:9999/fhir");
         genericClient.registerInterceptor(loggingInterceptor);
         // END SNIPPET: logging
      }

      /******************************/
      {
         // START SNIPPET: clientConfig
         // Create a client
         FhirContext ctx = new FhirContext();
         IPatientClient client = ctx.newRestfulClient(IPatientClient.class, "http://localhost:9999/");

         // Request JSON encoding from the server (_format=json)
         client.setEncoding(EncodingEnum.JSON);

         // Request pretty printing from the server (_pretty=true)
         client.setPrettyPrint(true);
         // END SNIPPET: clientConfig
      }
   }

}
