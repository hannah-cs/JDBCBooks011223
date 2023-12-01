package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/books")
public class BooksResource {

    @GET
    @Produces("application/json")
    public Response getBooks() {
        List<Book> books = new ArrayList<>();

        try {
            Connection connection = DatabaseManager.getConnection();
            System.out.println("Connected to the database successfully. All books:");

            String query = "SELECT * FROM books;";
            try (PreparedStatement pst = connection.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("price"),
                            rs.getInt("quantity")
                    );
                    books.add(book);
                }
                DatabaseManager.closeConnection(connection);

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Book book : books) {
                    if (!first) {
                        json.append(",");
                    }
                    json.append("{\"id\": ").append(book.getId())
                            .append(", \"title\": \"").append(book.getTitle())
                            .append("\", \"author\": \"").append(book.getAuthor())
                            .append("\", \"price\": ").append(book.getPrice())
                            .append(", \"quantity\": ").append(book.getQuantity())
                            .append("}");

                    first = false;
                }
                json.append("]");

                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Response getBookbyId(@PathParam("id") int id) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("price"),
                            rs.getInt("quantity")
                    );
                    books.add(book);
                }
                DatabaseManager.closeConnection(connection);
                StringBuilder json = new StringBuilder("[");
                boolean first = true;
                for (Book book : books) {
                    if (!first) {
                        json.append(",");
                    }
                    json.append("{\"id\": ").append(book.getId())
                            .append(", \"title\": \"").append(book.getTitle())
                            .append("\", \"author\": \"").append(book.getAuthor())
                            .append("\", \"price\": ").append(book.getPrice())
                            .append(", \"quantity\": ").append(book.getQuantity())
                            .append("}");
                    first = false;
                }
                json.append("]");
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    @GET
    @Path("/title/{title}")
    @Produces("application/json")
    public Response getBookbyTitle(@PathParam("title") String title) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM books WHERE title LIKE ?")) {
            pst.setString(1, "%"+ title + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("price"),
                            rs.getInt("quantity")
                    );
                    books.add(book);
                }
                DatabaseManager.closeConnection(connection);
                StringBuilder json = new StringBuilder("[");
                boolean first = true;
                for (Book book : books) {
                    if (!first) {
                        json.append(",");
                    }
                    json.append("{\"id\": ").append(book.getId())
                            .append(", \"title\": \"").append(book.getTitle())
                            .append("\", \"author\": \"").append(book.getAuthor())
                            .append("\", \"price\": ").append(book.getPrice())
                            .append(", \"quantity\": ").append(book.getQuantity())
                            .append("}");
                    first = false;
                }
                json.append("]");
                return Response.ok(json.toString(), MediaType.APPLICATION_JSON).build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}


//    @GET
//    @Path("/{title}")
//    @Produces("application/json")
//    public void getBooksbyTitle(String title){
//
//    }
//    @GET
//    @Path("/{author}")
//    @Produces("application/json")
//    public void getBooksbyAuthor(String author){
//
//    }
//
//    @POST
//    @Consumes("application/json")
//    public void addBook(String bookData) {
//
//    }
//    @PUT
//    @Path("/{id}")
//    @Consumes("application/json")
//    public void updateBook(@PathParam("id") int id, String bookData) {
//
//    }
//    @DELETE
//    @Path("/{id}")
//    public void deleteBook(@PathParam("id") int id) {
//
//    }