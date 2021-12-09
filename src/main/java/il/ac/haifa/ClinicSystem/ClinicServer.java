package il.ac.haifa.ClinicSystem;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.ac.haifa.ClinicSystem.entities.Clinic;
import il.ac.haifa.ClinicSystem.ocsf.server.AbstractServer;
import il.ac.haifa.ClinicSystem.ocsf.server.ConnectionToClient;

//import javax.mail.*;
//import javax.mail.internet.*;


public class ClinicServer extends AbstractServer{
	
	private static Session session;
	private static SessionFactory sessionFactory;
	private static Double curPrice = 43.99;
	private static Double futurePrice = -1.0;
	
	 private static SessionFactory getSessionFactory() throws HibernateException {
		 Configuration configuration = new Configuration();
		
		 // Add ALL of your entities here. You can also try adding a whole package.
		 configuration.addAnnotatedClass(Clinic.class);
		
		 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		 .applySettings(configuration.getProperties())
		 .build();
		
		 return configuration.buildSessionFactory(serviceRegistry);
	 }
	 
	 private static void generateClinics() throws Exception {
		 List<String> days = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
		 List<LocalTime> open = new ArrayList<>(), close = new ArrayList<>();
		 for(String d : days) {
		 	open.add(LocalTime.of(8,0));
		 	close.add(LocalTime.of(22,0));
		 }
		 Clinic c = new Clinic("The White Tower", "Tar Valon", open, close);
		 //session.saveOrUpdate(temp);
		 session.saveOrUpdate(c);
		 /*
		 * The call to session.flush() updates the DB immediately without ending the transaction.
		 * Recommended to do after an arbitrary unit of work.
		 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
		cache errors.
		 */
		 session.flush();
	 }
	 
	 private static <T> List<T> getAll(Class<T> object) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
			Root<T> rootEntry = criteriaQuery.from(object);
			CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
			
			TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
			return allQuery.getResultList();
	}

	public ClinicServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if(msg instanceof Clinic)  {
			try {
				 session = sessionFactory.openSession();
				 session.beginTransaction();
				 

				// Movie m = (Movie)session.load(Movie.class, ((Movie)msg).getId());
				// m.setDates(((Movie)msg).getDates());
				// m.setName("The dragon reborn");
				
			    session.merge((Clinic)msg);
				session.flush();
				List<Clinic> movies = getAll(Clinic.class);
				client.sendToClient(movies);
				 
			     

				 session.getTransaction().commit();
			 }catch (Exception exception) {
				 if (session != null) {
					 session.getTransaction().rollback();
					 }
					 System.err.println("An error occured, changes have been rolled back.");
					 exception.printStackTrace();
			 } finally {
					 session.close();
			 }
		}
		else if(((String) msg).equals("#ClinicList")) {
			 try {
				 session = sessionFactory.openSession();
				 session.beginTransaction();

				 List<Clinic> clinics = getAll(Clinic.class);
				 client.sendToClient(clinics);

				 session.getTransaction().commit();
			 }catch (Exception exception) {
				 if (session != null) {
					 session.getTransaction().rollback();
					 }
					 System.err.println("An error occured, changes have been rolled back.");
					 exception.printStackTrace();
			 } finally {
					 session.close();
			 }
		}
	}
	
	
	
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		// TODO Auto-generated method stub
		
		System.out.println("Client Disconnected.");
		super.clientDisconnected(client);
	}
	
	

	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		System.out.println("Client connected: " + client.getInetAddress());
	}

	public static void main(String[] args) throws IOException {
		
			 try {
				 sessionFactory = getSessionFactory();
				 session = sessionFactory.openSession();
				 session.beginTransaction();
				 
				 generateClinics();
				 //List<Movie> movies = getAll(Movie.class);
				 //System.out.println(movies);
				 

				 session.getTransaction().commit();
			 }catch (Exception exception) {
				 if (session != null) {
					 session.getTransaction().rollback();
					 }
					 System.err.println("An error occured, changes have been rolled back.");
					 exception.printStackTrace();
			 } finally {
					 session.close();
			 } 
		    //sessionFactory = getSessionFactory();
			 
			ClinicServer server = new ClinicServer(50001);
					// Integer.parseInt(args[0]));
			server.listen();
		
	}

}
