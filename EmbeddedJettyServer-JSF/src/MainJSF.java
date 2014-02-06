import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

public class MainJSF {

	public static void main(String args[]) throws Exception {
		Server server = new Server(8080);
		// MBeanContainer mbContainer = new MBeanContainer(
		// ManagementFactory.getPlatformMBeanServer());
		// server.addBean(mbContainer);

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar("./WebContent");
		ServletHandler servletHandler = new ServletHandler();
		servletHandler.addServletWithMapping(
				javax.faces.webapp.FacesServlet.class, "*.xhtml");
		servletHandler.addServletWithMapping(
				javax.faces.webapp.FacesServlet.class, "*.jsf");
		HandlerList handlerList = new HandlerList();
		handlerList.addHandler(webapp);
		handlerList.addHandler(servletHandler);
		server.setHandler(handlerList);
		server.start();
		Handler handlers[] = server.getHandlers();
		for (Handler handler : handlers) {
			System.out.println("Handler name: " + handler);
		}
		server.join();
		System.out.println("Server Stopped");
	}
}