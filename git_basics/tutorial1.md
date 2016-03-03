#Git Basics

This is a Git basics tutorial. In this tutorial we will cover basic git commands and some common scenario workflow on a single branch.  
Note that we will use git from command line so we need to make sure that git cmd is enabled.  
Note that you will be cloning and working with your own branch from the Bitbucket for this tutorial so make sure that your bitbucket user is created with your zuhlke mail and that the branch is created and the branch name is provided for your before you start this tutorial.  

Bitbucket repository that we will use is `https://bitbucket.org/zuehlke/gitworkshop`

##Table of content:  
1. [Installation](#markdown-header-installation)  
2. [Initial setup](#markdown-header-initial-setup)  
3. [Cloning the project](#markdown-header-cloning-the-project)  
4. [Checking the state of local repository](#markdown-header-checking-the-state-of-local-repository)  
5. [Viewing the Commit history](#markdown-header-viewing-the-commit-history)  
5. [Registering our repository to the GitCommander](#markdown-header-registering-our-repository-to-the-gitcommander)  
6. [Create and commit one change](#markdown-header-create-and-commit-one-change)  
7. [Create and commit multiple changes](#markdown-header-create-and-commit-mutliple-changes)  
8. [Pull changes from remote repository](#markdown-header-pull-changes-from-remote-repository)  
9. [Edit file content and commit changes](#markdown-header-edit-file-content-and-commit-changes)  
10. [Edit deleted file](#markdown-header-edit-deleted-file)  
11. [Resolving a content conflict](#markdown-header-resolving-a-content-conflict)  
12. [Revert file to previous commit](#markdown-header-revert-file-to-previous-commit)  
13. [References](#markdown-header-references)  

##Installation

Install Git - `https://git-scm.com/downloads`

After installing - Try to run `git --version` after installation.  
If git is not recognized as an internal or external command we need to set environment variable called Path to contain Git installation paths.  
Go to Control Panel -> System and Security -> System and open Advanced system settings. In the Advanced system settings window go to tab Advanced and click on Environment Variables..  
Edit System variable called Path to contain Git installation paths:
>C:\%PATH_TO_YOUR_GIT_INSTALLATION%\bin;C:\%PATH_TO_YOUR_GIT_INSTALLATION%\cmd  

Try to run `git --version.` If You are still having issues with running the git in command line don't hesitate to ask a colleague for help :)

##Initial setup

Now we will setup our user for the git. You should use your Zuhlke mail for setting up the user.

`git config --global user.name "John Doe"`  
`git config --global user.email johndoe@zuehlke.com`

You can always check the config with the command `git config --list`. This will list all your current config settings for the git.  
Note that if you call this command in your local repository list can contain more and different configuration setup.

##Cloning the project

Now let's navigate to the desires folder where we will clone our git project. For example I am using folder `C:\Users\ivma\Desktop\Git workshop\repo`.  
You can initialize your local git repository in this folder by running the command `git init` and then `git remote add origin git@bitbucket.org:your_username/your_repo_name.git` to add your repository as a remote repository.  
For more details you could have a look into resource `https://confluence.atlassian.com/bitbucket/import-code-from-an-existing-project-259358821.html `

**But for this tutorial we will clone existing project into our local repository**.

To clone remote repository run the command:

`git clone -b <your_branch_name> https://<your_bitbucket_username>@bitbucket.org/zuehlke/gitworkshop.git` - by providing username when cloning the project you will not be prompted for username by git for pull and push commands.

For example `git clone -b marko_ivanovic https://markoi_13@bitbucket.org/zuehlke/gitworkshop.git`

You will be prompt to type in your password to complete the clone.

>Note: You will be prompt to type your password for every git command related to the remote repository. For more details on how to store username and password you could have a look into resources `https://git-scm.com/docs/git-credential-store and for more options https://git-scm.com/book/en/v2/Git-Tools-Credential-Storage`.

After the clone command is finished you should see the new folder `gitworkshop` with it's content copied into your repository folder.  
We will be working with the `gitworkshop` project so note that all other git commands will be executed inside this folder.

##Checking the state of local repository

Now we will use command `git status`. This command will generate an output that tells us the state of the local repository. It also produces the action suggestions that can be executed in that current state. 

Let's execute git status command in our working directory:

>C:\Users\ivma\Desktop\Git workshop\repo\gitworkshop>git status  
>On branch marko_ivanovic  
>Your branch is up-to-date with 'origin/marko_ivanovic'.  
>
>nothing to commit, working directory clean

As we can see it tells us on which branch are currently working on and what is the state of our working directory.

##Viewing the Commit history

To view the commit history for the project we could call the `git log` command. This command outputs the list of commits with information about commit hash, author, date of commit and commit message.  
It can be used to track changes for specific files that can be reverted later.  
Note: To navigate through commit history use `arrow keys` and to quit type `:q` if the list is longer.

>C:\Users\ivma\Desktop\Git workshop\repo\gitworkshop>git log  
>commit c7938570285470009be27e9fc6b4020355e978be  
>Author: ivma <marko.ivanovic@zuehlke.com>  
>Date:   Tue Feb 16 12:24:54 2016 +0100  
>
>   start up project

##Registering our repository to the GitCommander

Now let's register our repository branch to our helper server `GitCommander`. This server will provide some services that will help us to simulate the work of another developer on the same code that we are working on.

The registration will help server to clone our project and commit and push the changes every time we call some of his execute commands/actions.

For registration we will need to send information about our git user name, url to of our repository and branch name that we are working on.  
This call will return your register ID. Every other call that we will use will require `your registration ID`. Write it down somewhere after you get it.  
To register call register URL with your parameters in place of `<brackets>`

`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=register&user=<your_bitbucket_username>&url=<gitrepourl>&branch=<your_branch_name>`

For this tutorial the `<gitrepourl>` will be `https://bitbucket.org/zuehlke/gitworkshop.git`

If you want to check your registries (maybe to retrieve forgotten ID) you can call GitCommander's action url:

`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=getMyRegistries&user=<your_bitbucket_username>`

This call will retrieve the list of your registries together with git URLs and branch names with dedicated IDs.

If you made a typo with some registration or just simply want to unregister you can do that with the command:

`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=unregister&ID=<your_registration_ID>&user=<your_bitbucket_username>`

This call will delete your registration with given `ID` from `GitCommander`.

##Create and commit one change

Now let's do some work on our project. We will create some changes and we will push them to the remote repository.
Since the theme of our project is the Solar System let's do some planet discovering.

Create new JavaScript file in the folder `gitworkshop/solar system/js` (create new folder named `js`) and name it name mercury.js with content:

    var planetInfo = {name: 'Mercury', diameter: '4,878 km', orbitTime: '88 Earth days'}
    
    function getMercuryName() {
 	   return planetInfo.name;
    }
    
    function getMercuryDiameter(){
       return planetInfo.diameter;
    }
    
    function getMercuryOrbitTime() {
       return planetInfo.orbitTime;
    }

Congratulations! You have discovered Mercury planet. Now let's share your discovery with the world.  
We will use a group of commands (called one at the time of course) to commit our changes from the working directory to our local repository and then push our changed local repository to remote repository.

Call `git status command`. You will notice that it detected the newly created folder for staging. The file is not yet detected because this folder is not tracked by git. Let's track the folder first and then stage the mercury file.  
Staging the file means that we are marking this file to be committed.

To track the new folder call command `git add .` - this command will start tracking the newly created folder. 

Now let's call `git status` again. It shows that new file is ready for commit. This means that the file mercury.js was staged automatically after tracking the js folder.

If the file wasn't staged we could stage the file with the call:
`git add mercury.js` - This will stage the mercury.js file for commit.

Now let's commit staged file by calling command:  
`git commit -m "I have discovered Mercury guys"` - This command commits staged files into our local repository. The parameter `-m` is a Commit message that we want to attach to our commit so that other developers can have easier understanding what have we pushed to the repository.  
As you can notice as a response message git provides the `short commit code` (7 characters) information in the [] brackets with your user name. The `full commit code` (the full hash code) can be seen via `git log` history.  
By calling command `git log` next we can see that our commit is now the part of commit history of our project.  
To synchronize the state of local repository with remote repository first we need to pull all the changes from remote repository in order not to override the change of other developers (in this case planet explorers).  
Command that gets the latest changes from the remote repository is `git pull`. This command will provide the information about the result of the pull command.  
Now that we synchronized the changes from the remote repository let's push our changes form the local repository by calling the command `git push`. This command also provides information about the result as a response.

>Note: git push command has a prequest to be completed successfully and that is that all remote changes are synchronized to the local repository. This means that the command will complete if nobody else pushed some change to the remote repository. In other case you will get rejected message and a suggestion that the command git pull must be completed successfully before proceeding.
>In our tutorial we are always calling git pull and git push as closing commands but you can try and call git push without the pull to see what happens.

Now the world knows what have you discovered, planet explorer.

##Create and commit multiple changes

Now that we now how to publish our changes let's create some new changes and push them to remote repository.

Create these new JavaScript files in the folder folder `gitworkshop/solar system/js`:  
File `venus.js` with content:

    var planetInfo = {name: 'Venus', diameter: '12,104 km', orbitTime: '225 Earth days'}
    
    function getVenusName() {
       return planetInfo.name;
    }
    
    function getVenusDiameter(){
 	  return planetInfo.diameter;
    }
 
    function getVenusOrbitTime() {
       return planetInfo.orbitTime;
    }

File `earth.js` with content:

    var planetInfo = {name: 'Earth', diameter: '12,760 km', orbitTime: '365 days'}
    
    function getEarthName() {
       return planetInfo.name;
    }
    
    function getEarthDiameter(){
       return planetInfo.diameter;
    }
    
    function getEarthOrbitTime() {
       return planetInfo.orbitTime;
    }

and file `mars.js` with content:

    var planetInfo = {name: 'Mars', diameter: '6,787 km', orbitTime: '687 Earth days'}
    
    function getMarsName() {
       return planetInfo.name;
    }
    
    function getMarsDiameter(){
       return planetInfo.diameter;
    }
    
    function getMarsOrbitTime() {
       return planetInfo.orbitTime;
    }

Now let's execute a new block of publishing commands to push our changes:  
`git status` - Note that with this command we can check the working directory state and see if there is any files that we need to stage/commit.  
`git add .` - In case when you have multiple changes that you what to stage and commit you can stage all files at once by calling this command instead of calling the add command for every file individually.  
`git status` - to check if have staged all files.  
`git commit -m "Discovered more planets"` - This will commit all changed files to our local repository.  
`git pull`
`git push`

Now we have publish new discovered planets to the world!

##Pull changes from remote repository

Let's check what discoveries have our colleague made.

Call GitCommander to discover other planets using url:

`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=discoverFourBigBrothers&ID=<your_registration_ID>`

After receiving successful response from the GitCommander execute next git commands:  
`git pull`  
`git log` - To check the commits made on our project by other developer

OK... It seems that there is some "planet" file called pluto.js that was misplaced in our solar system. Never mind then we will handle this later.

As we already explained the common usage of command `git log` from now onward `git log` is optional.

##Edit file content and commit changes

Now let's put discovered planets on our solar system map represented in the `index.html` file.

Edit `index.html` to show discovered planets except pluto by putting the content between `<body>` tags:  

    <div id="mercury" style="position: absolute; top: 341px; left: 229px;">
    <img src="images/mercury.png">
	    <script src="js/mercury.js"></script>
	    <script>
	    	document.querySelector('div#mercury > img').title = "Hi, I a'm " + getMercuryName() 
	    	+ ". My diameter is " + getMercuryDiameter() 
	    	+ " and I orbit the Sun in " + getMercuryOrbitTime();
	    </script>
    </div>
    <div id="venus" style="position: absolute; top: 321px; left: 283px;">
	    <img src="images/venus.png">
	    <script src="js/venus.js"></script>
	    <script>
	    	document.querySelector('div#venus > img').title = "Hi, I a'm " + getVenusName() 
	    	+ ". My diameter is " + getVenusDiameter() 
	    	+ " and I orbit the Sun in " + getVenusOrbitTime();
	    </script>
    </div>
    <div id="earth" style="position: absolute; top: 316px; left: 376px;">
	    <img src="images/earth.png">
	    <script src="js/earth.js"></script>
	    <script>
		    document.querySelector('div#earth > img').title = "Hi, I a'm " + getEarthName() 
		    + ". My diameter is " + getEarthDiameter() 
		    + " and I orbit the Sun in " + getEarthOrbitTime();
	    </script>
    </div>
    <div id="mars" style="position: absolute; top: 323px; left: 476px;">
	    <img src="images/mars.png">
	    <script src="js/mars.js"></script>
	    <script>
		    document.querySelector('div#mars > img').title = "Hi, I a'm " + getMarsName() 
		    + ". My diameter is " + getMarsDiameter() 
		    + " and I orbit the Sun in " + getMarsOrbitTime();
	    </script>
    </div>
    <div id="jupiter" style="position: absolute; top: 282px; left: 566px;">
	    <img src="images/jupiter.png">
	    <script src="js/jupiter.js"></script>
	    <script>
		    document.querySelector('div#jupiter > img').title = "Hi, I a'm " + getJupiterName() 
		    + ". My diameter is " + getJupiterDiameter() 
		    + " and I orbit the Sun in " + getJupiterOrbitTime();
	    </script>
    </div>
    <div id="saturn" style="position: absolute; top: 264px; left: 740px;">
	    <img src="images/saturn.png">
	    <script src="js/saturn.js"></script>
	    <script>
		    document.querySelector('div#saturn > img').title = "Hi, I a'm " + getSaturnName() 
		    + ". My diameter is " + getSaturnDiameter() 
		    + " and I orbit the Sun in " + getSaturnOrbitTime();
	    </script>
    </div>
    <div id="uranus" style="position: absolute; top: 312px; left: 909px;">
	    <img src="images/uranus.png">
	    <script src="js/uranus.js"></script>
	    <script>
		    document.querySelector('div#uranus > img').title = "Hi, I a'm " + getUranusName() 
		    + ". My diameter is " + getUranusDiameter() 
		    + " and I orbit the Sun in " + getUranusOrbitTime();
	    </script>
    </div>
    <div id="neptune" style="position: absolute; top: 318px; left: 1024px;">
	    <img src="images/neptune.png">
	    <script src="js/neptune.js"></script>
	    <script>
		    document.querySelector('div#neptune > img').title = "Hi, I a'm " + getNeptuneName() 
		    + ". My diameter is " + getNeptuneDiameter() 
		    + " and I orbit the Sun in " + getNeptuneOrbitTime();
	    </script>
    </div>

Push your changes by executing this set of commands:  
`git status` - to check which files are changed  
`git add .` / `git add index.html` - to stage our changes  
`git status` - just to check if have successfully staged our changed file  
`git commit -m "Putting planets on index.html"`  
`git pull`  
`git push`  

As we already demonstrated the common usage of command `git status` from now onward this command is optional.

##Edit deleted file

Open index.html to check if our planets have showed up. If there is a picture of a planet by hovering over it's image you can see the basic planet information.  
Check the content of pluto.js file. It seems that it is missing the information about it's diameter. Edit it's content to look like this:  

    var planetInfo = {name: 'Pluto', diameter: '2,301 km', orbitTime: '248 Earth years'}
   
    function getPlutoName() {
       return planetInfo.name;
    }
    
    function getPlutoDiameter(){
       return planetInfo.diameter;
    }
    
    function getPlutoOrbitTime() {
       return planetInfo.orbitTime;
    }

We will now demonstrate the effect of editing the file and publishing it which was deleted by the other developer.

First call `GitCommander` to delete file `pluto.js` by calling URL:

`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=removeFile&ID=<your_registration_ID>&fileName=pluto.js`

Now will execute the standard set of commands to push our changes up to the remote repository:  
You can either `git add .` or `git add pluto.js` to stage our changed file because it is the only change in our working directory so the effect is the same.  
`git commit -m "Commiting changes on pluto.js"`  
`git pull`

Notice the edit/delete effect. 
>C:\Users\ivma\Desktop\Git workshop\repo\gitworkshop>git pull  
>Password for 'https://markoi_13@bitbucket.org':  
>remote: Counting objects: 4, done.  
>remote: Compressing objects: 100% (4/4), done.  
>remote: Total 4 (delta 2), reused 0 (delta 0)  
>Unpacking objects: 100% (4/4), done.  
>From https://bitbucket.org/zuehlke/gitworkshop  
>   be3e595..cab6a13  marko_ivanovic -> origin/marko_ivanovic  
>CONFLICT (modify/delete): solar system/js/pluto.js deleted in cab6a13d4034a3fdb7  
>2fa4a5a6760d3653509e74 and modified in HEAD. Version HEAD of solar system/js/pluto.js left in tree.  
>Automatic merge failed; fix conflicts and then commit the result.  

Even if the file was deleted our step was the following one so we introduced Pluto to the world again. In this case a `edit/delete conflict` is created. `This conflict occurs when the same file is updated on one place and deleted on another`.  
To solve this you can either delete local file or just stage the file again and commit and push the changes to keep the file in the project.  
Execute this block of commands to reintroduce the pluto.js into project again:  
`git add .`  
`git commit - m "merging pluto.js conflict"`  
`git pull`  
`git push`  

>Note: this can be handy if somebody accidentally removes some file that is still needed in the project. This is one of the ways to retrieve the file state.

##Resolving a content conflict

Now we will simulate an editing conflict situation and resolve it by hand, and we will use poor Pluto to demonstrate this.  
This is also called `merge conflict` as the git fails to automatically merge changes to the same file. `This conflict occurs when the same line is changed on the same file`.

First call GitCommander to revoke pluto with URL: 
`http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=revokePluto&ID=<your_registration_ID>`

This will edit information about pluto in `index.html`.

Now edit `index.html` to show the information about pluto from `pluto.js`. The `index.html` should have body content like this:  

    <div id="mercury" style="position: absolute; top: 341px; left: 229px;">
    <img src="images/mercury.png">
    	<script src="js/mercury.js"></script>
    	<script>
    		document.querySelector('div#mercury > img').title = "Hi, I a'm " + getMercuryName() 
    		+ ". My diameter is " + getMercuryDiameter() 
    		+ " and I orbit the Sun in " + getMercuryOrbitTime();
    	</script>
    </div>
    <div id="venus" style="position: absolute; top: 321px; left: 283px;">
    	<img src="images/venus.png">
    	<script src="js/venus.js"></script>
    	<script>
    		document.querySelector('div#venus > img').title = "Hi, I a'm " + getVenusName() 
    		+ ". My diameter is " + getVenusDiameter() 
    		+ " and I orbit the Sun in " + getVenusOrbitTime();
    	</script>
    </div>
    <div id="earth" style="position: absolute; top: 316px; left: 376px;">
    	<img src="images/earth.png">
    	<script src="js/earth.js"></script>
    	<script>
    		document.querySelector('div#earth > img').title = "Hi, I a'm " + getEarthName() 
    		+ ". My diameter is " + getEarthDiameter() 
    		+ " and I orbit the Sun in " + getEarthOrbitTime();
    	</script>
    </div>
    <div id="mars" style="position: absolute; top: 323px; left: 476px;">
    	<img src="images/mars.png">
    	<script src="js/mars.js"></script>
    	<script>
    		document.querySelector('div#mars > img').title = "Hi, I a'm " + getMarsName() 
    		+ ". My diameter is " + getMarsDiameter() 
    		+ " and I orbit the Sun in " + getMarsOrbitTime();
    	</script>
    </div>
    <div id="jupiter" style="position: absolute; top: 282px; left: 566px;">
    	<img src="images/jupiter.png">
    	<script src="js/jupiter.js"></script>
    	<script>
    		document.querySelector('div#jupiter > img').title = "Hi, I a'm " + getJupiterName() 
    		+ ". My diameter is " + getJupiterDiameter() 
    		+ " and I orbit the Sun in " + getJupiterOrbitTime();
    	</script>
    </div>
    <div id="saturn" style="position: absolute; top: 264px; left: 740px;">
    	<img src="images/saturn.png">
    	<script src="js/saturn.js"></script>
    	<script>
    		document.querySelector('div#saturn > img').title = "Hi, I a'm " + getSaturnName() 
    		+ ". My diameter is " + getSaturnDiameter() 
    		+ " and I orbit the Sun in " + getSaturnOrbitTime();
    	</script>
    </div>
    <div id="uranus" style="position: absolute; top: 312px; left: 909px;">
    	<img src="images/uranus.png">
    	<script src="js/uranus.js"></script>
    	<script>
    		document.querySelector('div#uranus > img').title = "Hi, I a'm " + getUranusName() 
    		+ ". My diameter is " + getUranusDiameter() 
    		+ " and I orbit the Sun in " + getUranusOrbitTime();
    	</script>
    </div>
    <div id="neptune" style="position: absolute; top: 318px; left: 1024px;">
    	<img src="images/neptune.png">
    	<script src="js/neptune.js"></script>
    	<script>
    		document.querySelector('div#neptune > img').title = "Hi, I a'm " + getNeptuneName() 
    		+ ". My diameter is " + getNeptuneDiameter() 
    		+ " and I orbit the Sun in " + getNeptuneOrbitTime();
    	</script>
    </div>
    <div id="pluto" style="position: absolute; top: 341px; left: 1132px;">
    	<img src="images/pluto.png">
    	<script src="js/pluto.js"></script>
    	<script>
    		document.querySelector('div#pluto > img').title = "Hi, I a'm " + getPlutoName() 
    		+ ". My diameter is " + getPlutoDiameter() 
    		+ " and I orbit the Sun in " + getPlutoOrbitTime();
    	</script>
    </div>

Next, commit the changes with this set of commands and let's see what will happen:  
`git add .` or git add index.html to stage the changed file  
`git commit -m "Commiting changes for Pluto on index.html"`  
Now execute command `git pull` and notice the message that you will receive.

>C:\Users\ivma\Desktop\Git workshop\repo\gitworkshop>git pull  
>Password for 'https://markoi_13@bitbucket.org':  
>remote: Counting objects: 4, done.  
>remote: Compressing objects: 100% (4/4), done.  
>remote: Total 4 (delta 1), reused 0 (delta 0)  
>Unpacking objects: 100% (4/4), done.  
>From https://bitbucket.org/zuehlke/gitworkshop  
>   147402f..c6c18cc  marko_ivanovic -> origin/marko_ivanovic  
>Auto-merging solar system/index.html  
>CONFLICT (content): Merge conflict in solar system/index.html  
>Automatic merge failed; fix conflicts and then commit the result.  

The message states that there is conflict with the file content of `index.html`.

Now let's open the file and see what is the issue.  
As you can see every time when there is a conflict with file's content git marks it with specific characters as follows:  

    <<<<<<< HEAD
		document.querySelector('div#pluto > img').title = "Hi, I a'm " + getPlutoName() 
		+ ". My diameter is " + getPlutoDiameter() 
		+ " and I orbit the Sun in " + getPlutoOrbitTime();
    =======
		document.querySelector('div#pluto > img').title = "Hi, I a'm Poor Pluto and I am not a planet anymore :'( ";
    >>>>>>> c6c18cc4624fcd0d70a704a9d4281c19ce1fb625

The meaning of marking:

    <<<<<< HEAD
	    local code
    =========
    	remote code
    >>>>>>> master hash code of last commit

As shown in the markup above the content between the left arrows and the line shows your local changes and between the line and right arrows shows the remote changes.  

There are many tools to help resolve this kind of conflicts and they can be started with git command 'git mergetool'.   
Have a look at these resources for the base information about merge tools and how to configure them from the command line:  
`https://git-scm.com/docs/git-mergetool`  
`https://www.git-tower.com/learn/git/ebook/command-line/tools-services/diff-merge-tools`

In order to understand better the conflict resolving we will fix this conflict by hand with simple text editor.  
Resolve the conflict by hand by keeping the local changes and removing the remote changes. The content of the pluto div in the body of the 'index.html' file should look like this now:  

    <div id="pluto" style="position: absolute; top: 341px; left: 1132px;">
    	<img src="images/pluto.png">
    	<script src="js/pluto.js"></script>
    	<script>
    		document.querySelector('div#pluto > img').title = "Hi, I a'm " + getPlutoName() 
    		+ ". My diameter is " + getPlutoDiameter() 
    		+ " and I orbit the Sun in " + getPlutoOrbitTime();
    	</script>
    </div>

As you can see it is the same content as before the commit. To mark this conflict as resolved we need to commit our changes again:  
`git add .`  
`git commit -m "Resolved pluto.js"`  

Now let's try and push the changes to the remote repository:  
Execute `git push`.

As you can see we have successfully resolved our first merge conflict, even by hand. We still believe that the Pluto is a planet. Keep you hops up Pluto!

##Revert file to previous commit

Now we will demonstrate how to `revert` some file to the previous state to the previous commit, or in other words undo our changes.

Here is a resource of examples of how to revert a specific file to previous state `https://www.atlassian.com/git/tutorials/undoing-changes/git-checkout`

In this case, because our fellow planet explorers disagree that the Pluto is a planet we decided to go with the flow and revoke Pluto.  
This means that we will revert our changes on the `index.html` page.

We will demonstrate how to overwrite file in our working directory with the content from the remote repository and then commit it to our local repository and push it back to the remote repository again and by that marking it to the new state.  
For this purpose we will use `git checkout` command which will copy the desired file in our working directory. In our case we want to checkout the file for the specific commit state.
Now let's execute command `git log` to find the `revision/commit number` that we want to restore.

Execute `git log` and find `revision_number` of commit that has comment "Revoking the Pluto".  
Now execute command `git checkout <commit_number> "solar system/index.html"` which will copy the content of the `index.html` file from the specific `commit version` and it will automatically stage changed file.  
Call `git commit -m "Removing new changes on index.html"` to commit the changes to our local repository.  
Push the changes by calling the commands set:  
`git pull`  
`git push`  

Now you have pushed the newest changes and our little planet exploration has ended.

Congrats! You have experienced the basics of git. Of course there's more to come of what can we do with GIT.  

##References

For more free exploration you can have a look at the outside resources:  
`https://www.atlassian.com/git/tutorials/setting-up-a-repository/`  
`http://git-scm.com/book/en/v2`  
`http://marklodato.github.io/visual-git-guide/index-en.html`  
`https://help.github.com/`  
`http://rogerdudler.github.io/git-guide/`  


>Note: As we were working on your designated branch don't be afraid to play around a little more with this project.


Try out some other scenarios with the help of GitCommander. 
It supports:  
* File deletion: `http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=removeFile&ID=<your_registration_ID>&fileName=<filename.fileextention>`  
* Adding random content as new line in file:  `http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=addNewLineToFile&ID=<your_registration_ID>&fileName=<filename.fileextention>`  
* File line editing:  `http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=editFile&ID=<your_registration_ID>&fileName=<filename.fileextention>&lineNumber=<file_line_number_to_edit>&content=<content_to_write_in_file>`  
* Adding new random named file:  `http://gitcommander.eu-central-1.elasticbeanstalk.com/GitServlet?action=addNewFile&ID=<your_registration_ID>`  

Try recreating the conflict scenarios and resolve them.

Never hesitate to ask a colleague for help if needed. :)