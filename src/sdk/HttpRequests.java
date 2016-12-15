package sdk;

import com.sun.jersey.api.client.*;

import java.util.ArrayList;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
public class HttpRequests {

    static Client client = Client.create();

    public static ClientResponse post(String path, String data) {
        ClientResponse clientResponse = null;

        try {

            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded")
                    .path(path);

            clientResponse = webResource.accept("application/json").post(ClientResponse.class, data);
        }
        catch
                (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }






    public static ClientResponse get(String path) {
        ClientResponse clientResponse = null;

        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded")
                    .path(path);


            clientResponse = webResource.accept("application/json").get(ClientResponse.class);




        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

    public static ClientResponse put(String path, String data, ArrayList<String> headerinfo) {
        ClientResponse clientResponse = null;

        try{
            WebResource webresource = client

                    .resource("http://localhost:8080/server2_0_war_exploded")

                    .path(path);

            clientResponse = webresource.accept("application/json").header(headerinfo.get(0), headerinfo.get(1)).put(ClientResponse.class, data);
            System.out.println(headerinfo.get(0) + "--" + headerinfo.get(1));


        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }


}
