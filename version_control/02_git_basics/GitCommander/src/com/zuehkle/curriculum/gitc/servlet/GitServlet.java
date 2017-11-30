package com.zuehkle.curriculum.gitc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zuehkle.curriculum.gitc.GitExecutor;
import com.zuehkle.curriculum.gitc.UsersStorage;

/**
 * Servlet implementation class GitServlet
 */
@WebServlet(description = "GIT Commander access point", urlPatterns = { "/GitServlet" })
public class GitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GitServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String repsonseString = "";
		if (request.getParameterMap().containsKey("action")) {
			switch (request.getParameter("action")) {
			case "register": 
				if (request.getParameterMap().containsKey("user")
						&& request.getParameterMap().containsKey("url")) {
					
					String gitUserName = request.getParameter("user");
					String gitRepositoryURL = request.getParameter("url");
					long newId = UsersStorage.addNewRegistry(gitUserName, gitRepositoryURL);
					System.out.println("regitser: " + newId);
					repsonseString = "regitser: " + newId;
					
				} else {
					
					System.out.println("bad regitser. just be cool");
					repsonseString = "bad regitser. just be cool";
					
				}
				break;
			case "unregister": 
				if (request.getParameterMap().containsKey("ID")) {
					
					boolean deleted = UsersStorage.deletRegistry(Long.parseLong(request.getParameter("ID")));
					System.out.println("unregister: " + deleted);
					repsonseString = "unregister: " + deleted;
					
				} else {
					
					System.out.println("bad unregitser. just be cool");
					repsonseString = "bad unregitser. just be cool";
					
				}
				break;
			case "getMyRegistries": 
				if (request.getParameterMap().containsKey("user")) {
					
					Map<String, String> userRegistries = UsersStorage.getAllUserRegistries(request.getParameter("user"));
					if (!userRegistries.isEmpty()) {
						for (String registryId : userRegistries.keySet()) {
							repsonseString += "ID: " + registryId + ", REPO: " + userRegistries.get(registryId) + System.getProperty("line.separator");
						}
					} else {
						System.out.println("No registries for user " + request.getParameter("user"));
						repsonseString = "No registries for user " + request.getParameter("user");
					}
					
				} else {
					
					System.out.println("bad getMyRegistries. just be cool");
					repsonseString = "bad getMyRegistries. just be cool";
					
				}
				break;
			case "addNewFile": 
				if (request.getParameterMap().containsKey("ID")) {
					
					String newFileAdded = "";
					try {
						newFileAdded = GitExecutor.addNewFile(Long.parseLong(request.getParameter("ID")));
					} catch (Exception e) {
						System.out.println("something wrong happend with adding new file. just be cool");
						repsonseString = "something wrong happend with adding new file. just be cool";
					}
					if (newFileAdded.isEmpty()) {
						System.out.println("Bad registry, no new file added. just be cool");
						repsonseString = "Bad registry, no new file added. just be cool";
					} else {
						System.out.println("Added new file: " + newFileAdded);
						repsonseString = "Added new file: " + newFileAdded;
					}
					
				} else {
					
					System.out.println("bad addNewFile. just be cool");
					repsonseString = "bad addNewFile. just be cool";
					
				}
				break;
			case "addNewLineToFile": 
				if (request.getParameterMap().containsKey("ID")
						&& request.getParameterMap().containsKey("fileName")) {
					
					String fileEdited = "";
					try {
						fileEdited = GitExecutor.addNewLineInFile(Long.parseLong(request.getParameter("ID")), request.getParameter("fileName"));
					} catch (Exception e) {
						System.out.println("something wrong happend with adding new  line in file. just be cool");
						repsonseString = "something wrong happend with adding new  line in file. just be cool";
					}
					if (fileEdited.isEmpty()) {
						System.out.println("Bad registry, no new line in file added. just be cool");
						repsonseString = "Bad registry, no new line in file added. just be cool";
					} else {
						System.out.println("Added new line in file: " + fileEdited);
						repsonseString = "Added new line in file: " + fileEdited;
					}
					
				} else {
					
					System.out.println("bad addNewLineToFile. just be cool");
					repsonseString = "bad addNewLineToFile. just be cool";
					
				}
				break;
			case "removeFile": 
				if (request.getParameterMap().containsKey("ID")
						&& request.getParameterMap().containsKey("fileName")) {
					
					String filePathDeleted = "";
					try {
						filePathDeleted = GitExecutor.removeFile(Long.parseLong(request.getParameter("ID")), request.getParameter("fileName"));
					} catch (Exception e) {
						System.out.println("something wrong happend with adding new file. just be cool");
						repsonseString = "something wrong happend with adding new file. just be cool";
					}
					if (filePathDeleted.isEmpty()) {
						System.out.println("Bad filepath, no new file deleted. just be cool");
						repsonseString = "Bad filepath, no new file deleted. just be cool";
					} else {
						System.out.println("Deleted file: " + repsonseString);
						repsonseString = "Deleted file: " + repsonseString;
					}
					
				} else {
					
					System.out.println("bad removeFile. just be cool");
					repsonseString = "bad removeFile. just be cool";
					
				}
				break;
			default:
				System.out.println("bad actionName. just be cool");
				repsonseString = "bad actionName. just be cool";
				break;
			}
			
		} else {
			System.out.println("no action. just be cool");
			repsonseString = "no action. just be cool";
		}
		
		PrintWriter printWriter = response.getWriter();
		printWriter.print(repsonseString);
		printWriter.close();
		
	}

}
