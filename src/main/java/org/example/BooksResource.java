package org.example;
import javax.ws.rs.*;
@Path("/books")
public class BooksResource {
    @GET
    @Produces("application/json")
    public void getBooks(){

    }
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public void getBook(int id){

    }
    @GET
    @Path("/{title}")
    @Produces("application/json")
    public void getBooks(String title){

    }
    @GET
    @Path("/{author}")
    @Produces("application/json")
    public void getBooks(String author){

    }

    @POST
    @Consumes("application/json")
    public void addBook(String bookData) {

    }
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void updateBook(@PathParam("id") int id, String bookData) {

    }
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") int id) {

    }

}
