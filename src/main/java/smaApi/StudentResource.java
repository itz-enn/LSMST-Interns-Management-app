package smaApi;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/students")
public class StudentResource {
    private dbHelper helper = new dbHelper();

    // CREATE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(studentModel student) {
        try {
            boolean added = helper.addStudent(student);
            if (added) {
                return Response.status(Response.Status.CREATED).entity(student).build();
            } else {
                return Response.status(Response.Status.CONFLICT).entity("Student already exists").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating student").build();
        }
    }

    // READ (all students)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        try {
            ArrayList<studentModel> students = helper.getAllStudent();
            return Response.ok(students).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving students").build();
        }
    }

    // READ (single student)
    @GET
    @Path("/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("userID") String userID, @QueryParam("password") String password) {
        try {
            studentModel student = helper.getStudent(userID, password);
            if (student != null) {
                return Response.ok(student).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Student not found or incorrect password").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving student").build();
        }
    }

    // UPDATE
    @PUT
    @Path("/{userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("userID") String userID, studentModel student) {
        // Note: Your dbHelper doesn't have a functional updateStudent method yet
        helper.updateStudent(student);
        return Response.ok(student).build();
        // Once implemented, you should add proper error handling here
    }

    // DELETE
    @DELETE
    @Path("/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("userID") String userID) {
        try {
            studentModel student = new studentModel("", userID);  // password not needed for deletion
            boolean deleted = helper.deleteStudent(student);
            if (deleted) {
                return Response.ok("{\"message\":\"Student deleted successfully\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting student").build();
        }
    }
}