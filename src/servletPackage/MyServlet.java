package servletPackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "registration form for the workshop", urlPatterns = { "/MyServlet" })

public class MyServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String participantEmail = request.getParameter("participantEmail");
		String checkboxAnswer = request.getParameter("newsletter");
		String participantData = request.getParameter("participantName") + " "
				+ request.getParameter("participantSurname");

		BufferedWriter participant = new BufferedWriter(new FileWriter(
				"C:\\Users\\Anita\\eclipse-workspace\\SampleServlet\\src\\servletPackage\\participants.txt", true));
		participant.append(participantData);
		participant.newLine();
		participant.close();

		try {

			if (participantEmail.contains("@") && checkboxAnswer.equals("YES")) {

				BufferedWriter emailAddress = new BufferedWriter(new FileWriter(
						"C:\\Users\\Anita\\eclipse-workspace\\SampleServlet\\src\\servletPackage\\subscribers.txt",
						true));
				emailAddress.append(participantEmail);
				emailAddress.newLine();
				emailAddress.close();

				response.sendRedirect("SubscriptionPage.html");

			} else {
				// writer.println("If you want to subscibe to the newsletter, enter your email
				// address on the home page");
				response.sendRedirect("ConfirmationPage.html");
			}
			
		} catch (NullPointerException e) {
			response.sendRedirect("ConfirmationPage.html");
		}
	}
}
