package com.zuehkle.curriculum.gitc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.TreeWalk;

public class GitExecutor {
	
	private static final String FAKE_GIT_USER = "markoi_13";
	private static final String FAKE_GIT_USER_PASSWORD = "Pass4Bit+12";

	public static String addNewFile(long registryId) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
		
		String newFileName = "newFileByJGit_"+System.currentTimeMillis()+".txt";
		
		// prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();
        
        Map<String, String> userRegistry = UsersStorage.getUserRegistry(registryId);
        
        if (userRegistry == null) {
        	newFileName = "";
        	return newFileName;
        }

        String REMOTE_URL = userRegistry.get("gitRepo");
        
        // then clone
        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        Git gitCloned = Git.cloneRepository()
            .setURI(REMOTE_URL)
            .setDirectory(localPath)
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
            .call();
        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
        System.out.println("Having repository: " + gitCloned.getRepository().getDirectory());
        
        Git gitCommit = new Git(gitCloned.getRepository());
        
        // create the file
        File myfile = new File(gitCloned.getRepository().getDirectory().getParent(), newFileName);
        myfile.createNewFile();
        
        String content = "This is commited with JGit";

		FileWriter fw = new FileWriter(myfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
        
        gitCommit.add().addFilepattern(newFileName).call();

        // and then commit the changes
        gitCommit.commit()
        		.setAll(true)
                .setMessage("I have commited new file " + newFileName + " with JGit")
                .call();
        
        //push the changes of commit
        gitCommit.push()
        		 .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
        		 .call();
        gitCommit.close();

        System.out.println("Committed all changes to repository at " + gitCloned.getRepository().getDirectory());

        gitCloned.getRepository().close();
        
        FileUtils.deleteDirectory(new File(gitCloned.getRepository().getDirectory().getParent()));
        
        System.out.println("Folder "+gitCloned.getRepository().getDirectory().getParent()+" is deleted!");
        
        return newFileName;
	}
	
	public static String addNewLineInFile(long registryId, String filePattern) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
		
		String filepatternToEdit = "";
		
		// prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();
		
		Map<String, String> userRegistry = UsersStorage.getUserRegistry(registryId);
        
        if (userRegistry == null) {
        	return filepatternToEdit;
        }

        String REMOTE_URL = userRegistry.get("gitRepo");
		
		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        Git gitCloned = Git.cloneRepository()
            .setURI(REMOTE_URL)
            .setDirectory(localPath)
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
            .call();
        System.out.println("Having repository: " + gitCloned.getRepository().getDirectory());
        
        Ref head = gitCloned.getRepository().getRef(Constants.HEAD);

        // a RevWalk allows to walk over commits based on some filtering that is defined
        RevWalk walk = new RevWalk(gitCloned.getRepository());

        RevCommit commit = walk.parseCommit(head.getObjectId());
        RevTree tree = commit.getTree();
        System.out.println("Having tree: " + tree);

        TreeWalk treeWalk = new TreeWalk(gitCloned.getRepository());
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        
        while (treeWalk.next()) {
        	if (treeWalk.getPathString().contains(filePattern)) {
        		filepatternToEdit = treeWalk.getPathString();
        		System.out.println(" yeah mfaaaaaaaaa!!!!!!!!" + treeWalk.getPathString());
        		break;
        	}
        }
        treeWalk.close();
        walk.close();
        
        if (!filepatternToEdit.isEmpty()) {
        	Git gitCommit = new Git(gitCloned.getRepository());
            
            // create the file
            
            File fileForEdit = new File(filepatternToEdit);
            fileForEdit.createNewFile();
            
            String content = "////This is commited with JGit at " + new Date();

    		FileWriter fw = new FileWriter(fileForEdit.getAbsoluteFile());
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.newLine();
    		bw.append(content);
    		bw.close();
            
            gitCommit.add().addFilepattern(filepatternToEdit).call();
	        
	        // and then commit the changes
	        gitCommit.commit()
	        		.setAll(true)
	                .setMessage("I have edited file " + filepatternToEdit + " with JGit")
	                .call();
	        
	        //push the changes of commit
	        gitCommit.push()
	        		 .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
	        		 .call();
	        gitCommit.close();
	
	        System.out.println("Committed all changes to repository at " + gitCloned.getRepository().getDirectory());
	
	        gitCloned.getRepository().close();
	        
	        FileUtils.deleteDirectory(new File(gitCloned.getRepository().getDirectory().getParent()));
	        
	        System.out.println("Folder "+gitCloned.getRepository().getDirectory().getParent()+" is deleted!");
        } else {
        	System.out.println("jok ja");
        }
        
        return filepatternToEdit;
	}
	
	public static String removeFile(long registryId, String filePattern) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
		
		String filepatternToDelete = "";
		
		// prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();
		
		Map<String, String> userRegistry = UsersStorage.getUserRegistry(registryId);
        
        if (userRegistry == null) {
        	return filepatternToDelete;
        }

        String REMOTE_URL = userRegistry.get("gitRepo");
		
		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        Git gitCloned = Git.cloneRepository()
            .setURI(REMOTE_URL)
            .setDirectory(localPath)
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
            .call();
        System.out.println("Having repository: " + gitCloned.getRepository().getDirectory());
        
        Ref head = gitCloned.getRepository().getRef(Constants.HEAD);

        RevWalk walk = new RevWalk(gitCloned.getRepository());

        RevCommit commit = walk.parseCommit(head.getObjectId());
        RevTree tree = commit.getTree();
        System.out.println("Having tree: " + tree);

        TreeWalk treeWalk = new TreeWalk(gitCloned.getRepository());
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        
        while (treeWalk.next()) {
        	if (treeWalk.getPathString().contains(filePattern)) {
        		filepatternToDelete = treeWalk.getPathString();
        		System.out.println(" yeah mfaaaaaaaaa!!!!!!!!" + treeWalk.getPathString());
        		break;
        	}
        }
        treeWalk.close();
        walk.close();
        
        if (!filepatternToDelete.isEmpty()) {
        	Git gitCommit = new Git(gitCloned.getRepository());
	        gitCommit.rm().addFilepattern(filepatternToDelete).call();
	        
	        // and then commit the changes
	        gitCommit.commit()
	        		.setAll(true)
	                .setMessage("I have removed file " + filepatternToDelete + " with JGit")
	                .call();
	        
	        //push the changes of commit
	        gitCommit.push()
	        		 .setCredentialsProvider(new UsernamePasswordCredentialsProvider(FAKE_GIT_USER, FAKE_GIT_USER_PASSWORD))
	        		 .call();
	        gitCommit.close();
	
	        System.out.println("Committed all changes to repository at " + gitCloned.getRepository().getDirectory());
	
	        gitCloned.getRepository().close();
	        
	        FileUtils.deleteDirectory(new File(gitCloned.getRepository().getDirectory().getParent()));
	        
	        System.out.println("Folder "+gitCloned.getRepository().getDirectory().getParent()+" is deleted!");
        } else {
        	System.out.println("jok ja");
        }
        
        return filepatternToDelete;
	}
}
