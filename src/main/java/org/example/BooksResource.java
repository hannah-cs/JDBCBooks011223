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
    public Response getBooksbyTitle(@PathParam("title") String title) {
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
    @GET
    @Path("/author/{author}")
    @Produces("application/json")
    public Response getBooksbyAuthor(@PathParam("author") String author) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM books WHERE author LIKE ?")) {
            pst.setString(1, "%"+ author + "%");
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

    @POST
    @Produces("application/json")
    public Response createBook(Book newBook) {

        try {
            Connection connection = DatabaseManager.getConnection();

            String query = "INSERT INTO books (title, author, price, quantity) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, newBook.getTitle());
                pst.setString(2, newBook.getAuthor());
                pst.setDouble(3, newBook.getPrice());
                pst.setInt(4, newBook.getQuantity());
                int rowsAffected = pst.executeUpdate();
                DatabaseManager.closeConnection(connection);
                if (rowsAffected > 0) {
                    return Response.ok("Book created successfully", MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create book").build();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book updatedBook) {
        try {
            Connection connection = DatabaseManager.getConnection();

            if (bookExists(updatedBook.getId(), connection)) {
                String updateQuery = "UPDATE books SET title=?, author=?, price=?, quantity=? WHERE id=?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, updatedBook.getTitle());
                    updateStatement.setString(2, updatedBook.getAuthor());
                    updateStatement.setDouble(3, updatedBook.getPrice());
                    updateStatement.setInt(4, updatedBook.getQuantity());
                    updateStatement.setInt(5, updatedBook.getId());
                    int rowsAffected = updateStatement.executeUpdate();
                    DatabaseManager.closeConnection(connection);

                    if (rowsAffected > 0) {
                        return Response.ok("Book updated successfully", MediaType.APPLICATION_JSON).build();
                    } else {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity("Failed to update book").build();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                DatabaseManager.closeConnection(connection);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Book with ID " + updatedBook.getId() + " not found").build();
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    private boolean bookExists(int bookId, Connection connection) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM books WHERE id=?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setInt(1, bookId);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        }
    }
}
//    @DELETE
//    @Path("/{id}")
//    public void deleteBook(@PathParam("id") int id) {
//
//    }