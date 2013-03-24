package org.jboss.as.quickstarts.kitchensink.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.as.quickstarts.kitchensink.model.Member;

import com.hout.client.ClientApi;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/members")
@RequestScoped
public class MemberResourceRESTService {
   @Inject
   private EntityManager em;
   
   @Inject
   private ClientApi clientApi;

   @GET
   @Produces("text/xml")
   public List<Member> listAllMembers() {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
      final List<Member> results = em.createQuery("select m from Member m order by m.name").getResultList();
      clientApi.createNewUser("123", "123", null);
      return results;
   }
   
   @GET
   @Path("/createEvent")
   @Produces("text/xml")
   public Boolean createEvent(@QueryParam("name") String name, @QueryParam("location") String profilePictureLocation) {
	   clientApi.createNewUser(name, profilePictureLocation, null);
	   return true;
   }
}