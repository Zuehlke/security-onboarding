#Git Basics

##What is Git?
Git is a widely used version control system for software development. It is a **distributed version control system (DVCS)** with an emphasis on speed, data integrity, and support for distributed, non-linear workflows.

![Images](centralize_vs_distributed_vcs.png)

In a DVCS clients don’t just check out the latest snapshot of the files: they fully mirror the repository. Thus if any server dies, and these systems were collaborating via it, any of the client repositories can be copied back up to the server to restore it. Every clone is really a full backup of all the data.

##Creating and cloning repository
If you want to create local repository, create a new directory, open it and perform in console following command:

	git init

If you want to create a working copy of existing local repository, run the command: 
	
	git clone /path/to/repository

When using a remote server, command will be like:

	git clone username@host:/path/to/repository

 
##Typical workflow 

Local repository consists of three "trees" maintained by git. The first one is **Working Directory** which holds the actual files. The second one is the `Index` which acts as **staging area** and finally the `HEAD` which points to the last commit you've made in your **local git repository**.

![Images](git_workflow.png)

The typical workflow goes like this: - you create/edit/modify a file inside your repository - you **stage** the changes to the staging area - you **commit** these changes which creates a permanent snapshot of the file in the Git directory along with a message that indicates what you did to the file.

##Recording Changes to the Repository
###Commands: `status`, `add`,  `commit`
 
The main tool you use to determine which files are in which state is the `git status` command. If you run this command directly after a clone, you should see something like this:

	$ git status
	On branch master
	nothing to commit, working directory clean

This means you have a clean working directory – in other words, there are no tracked and modified files. 

Let’s say you add a new file to your project, a simple README file. If the file didn’t exist before, and you run `git status`, you see your untracked file like so:

	$ git status
	On branch master
	Untracked files:
	  (use "git add <file>" to include in what will be committed)
	
	    README
	
	nothing added to commit but untracked files present (use "git add" to track)

You can see that your new README file is untracked. Untracked basically means that Git sees a file you didn’t have in the previous snapshot (commit). 

In order to begin tracking a new file, you use the command git add. To begin tracking the README file, you can run this:

	$ git add README

Running status command again, you can see that your README file is now tracked and staged to be committed:

	$ git status
	On branch master
	Changes to be committed:
	  (use "git rm HEAD --cached <file>..." to unstage)
	
	    new file:   README

Now that your staging area is set up the way you want it, you can commit your changes. The simplest way to commit your changes is following:

	git commit -m "Added README file"

Message after flag `-m` is mandatory and it has to be meaningful regarding on commit.  

**Skipping the Staging Area**

Adding the `-a` option to the `git commit` command makes Git automatically stage every file that is already tracked before doing the commit, letting you skip the `git add` part.
If you update your README file, you can stage and commit changes with following command:

	git commit -am "Updated README file"

##Viewing the Commit History

After you have created several commits, or if you have cloned a repository with an existing commit history, you’ll probably want to look back to see what has happened. The most basic and powerful tool to do this is the `git log` command.

	$ git log
	commit ca82a6dff817ec66f44342007202690a93763949
	Author: mikr <milan.krunic@zuehlke.com>
	Date:   The Oct 15 14:15:29 2015 +0200

    	Updated README file

	commit 085bb3bcb608e1e8451d4b2432f8ecbe6306e7e7
	Author: mikr <milan.krunic@zuehlke.com>
	Date:   The Oct 15 14:15:25 2015 +0200
	
	    Added README file


##Git Branching
- *Branching, in revision control and software configuration management, is the duplication of an object under revision control (such as a source code file, or a directory tree) so that modifications can happen in parallel along both branches* - Wikipedia

- Branching means you diverge from the main line of development and continue to do work without messing with that main line.

//TODO 

##Working with Remotes

To be able to collaborate on any Git project, you need to know how to manage your remote repositories. Remote repositories are versions of your project that are hosted on the Internet or network somewhere. Collaborating with others involves managing these remote repositories and pushing and pulling data to and from them when you need to share work.

To see which remote servers you have configured, you can run the `git remote` command.

If you’ve cloned your repository, you should at least see **origin** – that is the default name Git gives to the server you cloned from.

	$ git remote
	origin

To add a new remote Git repository as a shortname you can reference easily, run `git remote add [shortname] [url]`

	$ git remote add gitintro https://github.com/milankr/zrs-curriculum-test.git
Now you can use the string **gitintro** on the command line in lieu of the whole URL.

###Fetching and Pulling from Remotes
To get data from your remote projects, you can run:
	
	$ git fetch [remote-name] 
 
It’s important to note that the git fetch command pulls the data to your local repository – it doesn’t automatically merge it with any of your work or modify what you’re currently working on. You have to merge it manually into your work when you’re ready.

Using `git pull` command you automatically **fetch** and then **merge** a remote branch into your current branch.

###Pushing to Remotes

When you have your project at a point that you want to share, you have to push it upstream. The command for this is simple: 

	git push [remote-name] [branch-name]

If you want to push your master branch to your origin server (again, cloning generally sets up both of those names for you automatically), then you can run this to push any commits you’ve done back up to the server:
	
	$ git push origin master