package com.zuehkle.curriculum.gitc;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;



/**
 * Simple snippet which shows how to clone a repository from a remote source
 *
 * @author dominik.stadler at gmx.at
 */
public class GitTest {

    private static final String REMOTE_URL = "https://markoi_13@bitbucket.org/zuehlke/beg_camp_2015_team_frontend.git";

    public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
        // prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();
//
//        // then clone
//        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
//        try (Git result = Git.cloneRepository()
//                .setURI(REMOTE_URL)
//                .setDirectory(localPath)
//                .setCredentialsProvider(new UsernamePasswordCredentialsProvider("markoi_13", "Pass4Bit+12"))
//                .call()) {
//	        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
//	        System.out.println("Having repository: " + result.getRepository().getDirectory());
//	        
//	        Git gitCommit = new Git(result.getRepository());
//	        
//	     // create the file
//	        File myfile = new File(result.getRepository().getDirectory().getParent(), "newFileByJGit.txt");
//	        myfile.createNewFile();
//	        
//	        String content = "This is also commited with JGit";
//
//			FileWriter fw = new FileWriter(myfile.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(content);
//			bw.close();
//	        
//	        gitCommit.add().addFilepattern("newFileByJGit.txt").call();
//
//	        // and then commit the changes
//	        gitCommit.commit()
//	        		.setAll(true)
//	                .setMessage("I have commited another file with JGit")
//	                .call();
//	        gitCommit.push().setCredentialsProvider(new UsernamePasswordCredentialsProvider("markoi_13", "Pass4Bit+12")).call();
//	        gitCommit.close();
//
//	        System.out.println("Committed all changes to repository at " + result.getRepository().getDirectory());
//
//            // workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=474093
//	        result.getRepository().close();
//	        
//	        FileUtils.deleteDirectory(new File(result.getRepository().getDirectory().getParent()));
//	        
//	        System.out.println("Folder "+result.getRepository().getDirectory().getParent()+" is deleted!");
//        }
    	
        
        
    	System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        Git gitCloned = Git.cloneRepository()
            .setURI(REMOTE_URL)
            .setDirectory(localPath)
            .setCredentialsProvider(new UsernamePasswordCredentialsProvider("markoi_13", "Pass4Bit+12"))
            .call();
        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
        System.out.println("Having repository: " + gitCloned.getRepository().getDirectory());
        
        Git gitCommit = new Git(gitCloned.getRepository());
        
        Ref head = gitCloned.getRepository().getRef("HEAD");

        // a RevWalk allows to walk over commits based on some filtering that is
        // defined
        RevWalk walk = new RevWalk(gitCloned.getRepository());

        RevCommit commit = walk.parseCommit(head.getObjectId());
        RevTree tree = commit.getTree();
        System.out.println("Having tree: " + tree);

        // now use a TreeWalk to iterate over all files in the Tree recursively
        // you can set Filters to narrow down the results if needed
        TreeWalk treeWalk = new TreeWalk(gitCloned.getRepository());
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        String filepatternToDelete = "";
        while (treeWalk.next()) {
        	if (treeWalk.getPathString().contains("fileByJGit.txt")) {
        		filepatternToDelete = treeWalk.getPathString();
        		System.out.println(" yeah mfaaaaaaaaa!!!!!!!!" + treeWalk.getPathString());
        	} else if (treeWalk.getPathString().contains("GPSPosition")) {
        		System.out.println(" double yeah mfaaaaaaaaa!!!!!!!!" + treeWalk.getPathString());
        	} else {
        		System.out.println("found: " + treeWalk.getPathString());
        	}
        }
        treeWalk.close();
        
        if (!filepatternToDelete.isEmpty()) {
	        gitCommit.rm().addFilepattern(filepatternToDelete).call();
	        
	        // and then commit the changes
	        gitCommit.commit()
	        		.setAll(true)
	                .setMessage("I have removed file fileBYJGit with JGit take 3")
	                .call();
	        
	        //push the changes of commit
	        gitCommit.push()
	        		 .setCredentialsProvider(new UsernamePasswordCredentialsProvider("markoi_13", "Pass4Bit+12"))
	        		 .call();
	        gitCommit.close();
	
	        System.out.println("Committed all changes to repository at " + gitCloned.getRepository().getDirectory());
	
	        // workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=474093
	        gitCloned.getRepository().close();
	        
	        FileUtils.deleteDirectory(new File(gitCloned.getRepository().getDirectory().getParent()));
	        
	        System.out.println("Folder "+gitCloned.getRepository().getDirectory().getParent()+" is deleted!");
        } else {
        	System.out.println("jok ja");
        }
    }
}