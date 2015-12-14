Subversion Basics
=================

 

What is Subversion?
-------------------

 

Apache Subversion aka **SVN **is a *software versioning* and *revision control
system* distributed as free software under Apache License. Its main goal is to
be the most compatible successor to the previously widely used **CVS**
(Concurrent Versions System). It is a **centralized version control system**
**(CVCS)** with emphasis on commits as true atomic operations, versioning of
binary files, predictable branching, file locking, merge tracking and
reliability.

 

Unlike Git, SVN is a centralized version control system.

![](<img/svn-development.png>)

In CVCS clients just check out the latest snapshot of the files in the central
repository. They do not mirror the repository. If the central server goes down
it is not possible to clone the repository from another user like in Git.

 

Subversion’s Architecture
-------------------------

The following image illustrates a “mile-high” view of Subversion’s design

![](<img/subversion-architecture.png>)

On one end is a Subversion repository that holds all of your versioned data. On
the other end is your Subversion client program, which manages local parts of
that versioned data. Between these extremes are multiple routes through a
Repository Access (RA) layer, some of which go across computer networks and
through network servers which then access the repository, others of which bypass
the network altogether and access the repository directly.

 

Subversion’s Components
-----------------------

Subversion, once installed, has a number of different pieces and command line
utilities. In the following section we will try to mention the most important
ones:

-   **svn **- The command-line client program

-   **svnversion **- A program for reporting the state (in terms of revisions of
    the items present) of a working copy

-   **svnlook **- A tool for directly inspecting a Subversion repository

-   **svnadmin **- A tool for creating, tweaking, or repairing a Subversion
    repository

-   **mod\_dav\_svn **- A plug-in module for the Apache HTTP Server, used to
    make your repository available to others over a network

-   **svnserve **- A custom standalone server program, runnable as a daemon
    process or invokable by SSH; another way to make your repository available
    to others over a network

-   **svndumpfilter **- A program for filtering Subversion repository dump
    streams

-   **svnsync **- A program for incrementally mirroring one repository to
    another over a network

-   **svnrdump **- A program for performing repository history dumps and loads
    over a network

 

Creating The Repository
-----------------------

Subversion repository creation is a simple task. The svnadmin utility provides a
sub command for this. Once svn is installed on your server issue the following
command

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svnadmin create ~/myrepo
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Edit repository settings  in:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ vim ~/myrepo/conf/svnserve.conf
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

And run the svn server as a daemon:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svnserver -d
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Checking-out The Repository
---------------------------

After we’ve setup a repository on the central svn server we can check it out on
our local machine

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ cd ~/projects 
$ svn co svn+ssh://username@host:port/path/myrepo
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Typical Workflow
----------------

Local repository consists of svn index and files checked-out from central
repository.

Developers check-out the changes from the central server, edit working copy
files, resolve conflicts, and publish changes with the *svn commit* command

 

![](<img/svn_workflow_ongoing.png>)

 

Adding a file:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn add test.txt
A         test.txt
$ svn status
A         test.txt
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

“A” indicates file is scheduled for addition.

 

Running diff:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn diff
Index: test.txt
===================================================================
--- test.txt   (revision 0)
+++ test.txt   (revision 0)
@@ -0,0 +1 @@
+This is a test file 

Property changes on: test.txt
__________________________________________________________________
Added: svn:eol-style
   + native
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

And finally committing a file:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn commit test.txt -m "added test.txt"
Adding         test.txt
Transmitting file data .
Committed revision 2.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Update working copy:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn update
U    test.txt
Updated to revision 3.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

"U" indicates an "Update" to a file or directory

 

Modify the test file:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ vim test.txt
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Check the status:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn status
M       test.txt
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

“M” indicates file has been “Modified”

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn diff
Index: test.txt
===================================================================
--- test.txt   (revision 3)
+++ test.txt   (working copy)
@@ -1,2 +1,3 @@
 This is a test file
 This is a new line added by Fred.
+This line added by me.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Suppose someone edits the same line as you before you commit:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn update
Conflict discovered in 'test.txt'.
Select: (p) postpone, (df) diff-full, (e) edit,
        (mc) mine-conflict, (tc) theirs-conflict,
        (s) show all options:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If you choose *e* you can edit both sets of changes. You can save changes in the
editor and then select *r* (for resolved)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
G    test.txt
Updated to revision 4.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

"G" indicates "merGed"

 

### Committing changes

Only committers can commit directly to the repository:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn commit test.c --username your-name --password your-password \
  -m "added new C file"
Sending        test.c
Transmitting file data .
Committed revision 5.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

In general, you may not have to include always your username or password if you
do a proper setup of your ssh key or have subversion store the password.

 

### Commit message

Log comments are important. Information like author, where the change
start/ends, the date, the bug/issue id, and the author don't really belong in
the code as SVN can keep it much more effectively without altering the coding
style. Always try to use a log file for your commits.

 

Viewing the Commit History
--------------------------

For generating a list of historical changes use the *svn log* command

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn log
------------------------------------------------------------------------
r1 | VisualSVN Server | 2012-12-13 15:10:45 +0100 (ned, 13 dec 2012) | 1 line
Initial structure.
------------------------------------------------------------------------
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

This command will provide you with a record of who made changes at what revision
an time and date. Note that the log messages are printed in *reverse
chronological order* by default. If you wish to see a different range of
revisions in a particular order or just a single revision, pass
the `--revision` (`-r`) option:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
svn log -r 5:19 - Display logs for revisions 5 through 19 in chronological order
svn log -r 19:5 - Display logs for revisions 5 through 19 in reverse order 
svn log -r 8      Display logs for revision 8 only
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

You can examine the log history of a single file or directory:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn log foo.c
…
$ svn log http://foo.com/svn/trunk/code/foo.c
…
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

If you want even more information about a file or directory, **svn log** also
takes a `--verbose` (`-v`) option. Because Subversion allows you to move and
copy files and directories, it is important to be able to track path changes in
the file system. So, in verbose mode, **svn log** will include a list of changed
paths in a revision in its output:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn log -r 8 -v
------------------------------------------------------------------------
r8 | zeljko | 2012-05-21 13:19:25 -0500 (Wed, 21 May 2012) | 1 line
Changed paths:
   M /trunk/code/foo.c
   M /trunk/code/bar.h
   A /trunk/code/doc/README

Frozzled the sub-space witch.
------------------------------------------------------------------------
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

**svn log** also takes a `--quiet` (`-q`) option, which suppresses the body of
the log message. When combined with `--verbose` (`-v`), it gives just the names
of the changed files.

 

Subversion Branching and Merging
--------------------------------

 

### What is a branch?

Branching represents a basic concept of *branch *- *a line of development that
exists independently of another line*, yet still shares common history. Branch
always begins a life as a copy of something. New branch then has its own
history.

![](<img/branches1.png>)

 

### Using branches in SVN

Each commit creates a new state in the file system, called *revision*. Branching
allows changing file system in parallel as independent development lines.

 

### Creating a branch

Creating a branch is very simple. You make a copy of your project tree in the
repository using the *svn copy* command. Since your project’s source is rooted
in the *trunk* directory:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn copy http://svn.example.com/repos/myproject/trunk \
           http://svn.example.com/repos/myproject/branches/test-branch \
           -m "Creating a private branch of /myproject/trunk."

Committed revision 341.
$
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

This command causes a near-instantaneous commit in the repository, creating a
new directory in revision 341. The new directory is a copy of
*/myproject/trunk.*

 

### Working with your branch

Now that your branch is created, you can check out a new working copy to start
using it

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn checkout http://svn.example.com/repos/myproject/branches/test-branch
A  test-branch/Makefile
A  test-branch/integer.c
A  test-branch/button.c
Checked out revision 341.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

There is nothing special about this working copy, it just mirrors a different
directory in the repository

 

Let's pretend that a week goes by, and the following commits happen:

-   You make a change to `/myproject/branches/test-branch/button.c`, which
    creates revision 342.

-   You make a change to `/myproject/branches/test-branch/integer.c`, which
    creates revision 343.

-   Fred makes a change to `/myproject/trunk/integer.c`, which creates revision
    344.

Now two independent lines of development are happening on `integer.c`.

 

![](<img/svn-branches.png>)

 

To see how this timeline is represented in Subversion let’s  see the log of the
integer.c

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ cd ~/myproject/branches/test-branch
$ svn log -v integer.c
------------------------------------------------------------------------
r343 | user | 2012-11-07 15:27:56 -0600 (Thu, 07 Nov 2012) | 2 lines
Changed paths:
   M /myproject/branches/test-branch/integer.c

* integer.c:  frozzled the wazjub.
------------------------------------------------------------------------
r341 | user | 2012-11-03 15:27:56 -0600 (Thu, 07 Nov 2012) | 2 lines
Changed paths:
   A /myproject/branches/test-branch (from /myproject/trunk:340)

Creating a private branch of /myproject/trunk.
------------------------------------------------------------------------
r303 | fred | 2012-10-29 21:14:35 -0600 (Tue, 29 Oct 2012) | 2 lines
Changed paths:
   M /myproject/trunk/integer.c

* integer.c:  changed a docstring.
------------------------------------------------------------------------
r98 | fred | 2012-02-22 15:35:29 -0600 (Fri, 22 Feb 2012) | 2 lines
Changed paths:
   A /myproject/trunk/integer.c

* integer.c:  adding this file to the project.
------------------------------------------------------------------------
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

It is important to note that Subversion has no internal concept of a branch—it
knows only how to make copies! When you copy a directory, the result directory
is only a “branch” because you attach the meaning to it. Subversion treats that
“branch” as an ordinary directory that happens to carry some extra historical
information.

Subversion branches exist as* normal file system directories* in the repository.
Also, the location of the branch directory does not matter to Subversion, it is
by the convention that */branches*  is the parent directory.

 

### Basic Merging

Let's suppose that a week has passed since you started working on your private
branch. Your new feature isn't finished yet, but at the same time you know that
other people on your team continue to make important changes in the
project's */trunk*. It's in your best interest to replicate those changes to
your own branch, just to make sure they mesh well with your changes. This is
done by performing a *sync merge*—a merge operation designed to bring your
branch up to date with any changes made to its ancestral parent branch since
your branch was created.



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ pwd 
~/myproject/branches/test-branch
$ svn merge ^/myproject/trunk
--- Merging r345 through r356 into '.':
U    button.c
U    integer.c
--- Recording mergeinfo for merge of r345 through r356 into '.':
U   .
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

After running previous example, branch working copy now contains new local
modifications, which represent the changes on the trunk:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn status
 M      .
 M       button.c
 M       integer.c
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

At this point it is wise to inspect all changes using* svn diff*. After
everything is resolved, you need to commit the merge to the repository:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn commit -m "Merged latest trunk changes to test-branch."
Sending        .
Sending        button.c
Sending        integer.c
Transmitting file data ..
Committed revision 357.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

After this commit, your branch is in “sync” with the trunk.

Suppose that another week has passed. You've committed more changes to your
branch, and your colleagues have continued to improve the trunk as well. Once
again, you want to replicate the latest trunk changes to your branch and bring
yourself in sync. Just run the same merge command again:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge ^/myproject/trunk 
svn: E195020: Cannot merge into mixed-revision working copy [357:378]; try up\
dating first
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

If that was unexpected, just remember that working copy contains a mixture of
revisions. To fix the previous error. run:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn up
Updating '.':
At revision 380.

$ svn merge ^/myproject/trunk
--- Merging r357 through r380 into '.':
U    integer.c
U    Makefile
A    README
--- Recording mergeinfo for merge of r357 through r380 into '.':
U   .
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Subversion knows which trunk changes you previously replicated to your branch,
so it carefully replicates only those changes you don't yet have. And once
again, you build, test, and **svn commit** the local modifications to your
branch.

 

### Reintegrating a Branch

When you finish your work, you bring your branch in sync with the trunk again:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge ^/myproject/trunk
--- Merging r381 through r385 into '.':
U    button.c
U    README
--- Recording mergeinfo for merge of r381 through r385 into '.':
 U   .

$ # build, test, ...

$ svn commit -m "Final merge of trunk changes to test-branch."
Sending        .
Sending        button.c
Sending        README
Transmitting file data ..
Committed revision 390.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Now, use **svn merge** with the `--reintegrate` option to replicate your branch
changes back into the trunk. You'll need a working copy of `/trunk`.  Once you
have a clean working copy of the trunk, you're ready to merge your branch back
into it:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn update  # (make sure the working copy is up to date)
Updating '.':
At revision 390.

$ svn merge --reintegrate ^/myproject/branches/test-branch
--- Merging differences between repository URLs into '.':
U    button.c
U    integer.c
U    Makefile
--- Recording mergeinfo for merge between repository URLs into '.':
U   .

$ # build, test, verify, ...

$ svn commit -m "Merge my-calc-branch back into trunk!"
Sending        .
Sending        button.c
Sending        integer.c
Sending        Makefile
Transmitting file data ..
Committed revision 391.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Now that your private branch is merged to trunk, you may wish to remove it from
the repository:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn delete ^/myproject/branches/test-branch \
             -m "Remove test-branch, reintegrated with trunk in r391."
Committed revision 392.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

### Undoing changes

An extremely common use for **svn merge** is to roll back a change that has
already been committed. Suppose you're working on a working copy
of `/myproject/trunk`, and you discover that the change made way back in
revision 303, which changed `integer.c`, is completely wrong. It never should
have been committed. You can use **svn merge** to“undo” the change in your
working copy, and then commit the local modification to the repository. All you
need to do is to specify a *reverse* difference. (You can do this by
specifying `--revision 303:302`, or by an equivalent `-c/--change -303`):

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge -c -303 ^/myproject/trunk
--- Reverse-merging r303 into 'integer.c':
U    integer.c
--- Recording mergeinfo for reverse merge of r303 into 'integer.c':
U   A-branch

$ svn status
M      .
M      integer.c

$ svn diff
…
# verify that the change is removed
…

$ svn commit -m "Undoing change committed in r303."
Sending        integer.c
Transmitting file data .
Committed revision 350.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Keep in mind that rolling back a change like this is just like any other **svn
merge** operation, so you should use **svn status** and **svn diff** to confirm
that your work is in the state you want it to be in, and then use **svn
commit** to send the final version to the repository. After committing, this
particular changeset is no longer reflected in the `HEAD` revision.

 

### Restoring Deleted Items

First, you might need to use **svn log** to discover the exact coordinate pair
you wish to resurrect. A good strategy is to run `svn log --verbose` in a
directory that used to contain your deleted item. The `--verbose` (`-v`) option
shows a list of all changed items in each revision; all you need to do is find
the revision in which you deleted the file or directory. You can do this
visually, or by using another tool to examine the log output (via **grep**, or
perhaps via an incremental search in an editor):

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn log -v
…
------------------------------------------------------------------------
r808 | zeljko | 2003-12-26 14:29:40 -0600 (Fri, 26 Dec 2013) | 3 lines
Changed paths:
   D /calc/trunk/real.c
   M /calc/trunk/integer.c

Added fast fourier transform functions to integer.c.
Removed real.c because code now in double.c.
…
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

As we can see from the previous listing the file was deleted in revision 808, so
we need to get 807 to our working copy:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn copy ^/myproject/trunk/real.c@807 ./real.c

$ svn status
A  +    real.c

$ svn commit -m "Resurrected real.c from revision 807, /myproject/trunk/real.c."
Adding         real.c
Transmitting file data .
Committed revision 1390.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

### Advanced merging

The next section describes the fully expanded syntax of the command and
discusses a number of common scenarios that require it.

 

### Cherrypicking

Just as the term “changeset” is often used in version control systems, so is the
term *cherrypicking*. This word refers to the act of choosing *one* specific
changeset from a branch and replicating it to another. Cherrypicking may also
refer to the act of duplicating a particular set of (not necessarily
contiguous!) changesets from one branch to another. This is in contrast to more
typical merging scenarios, where the “next” contiguous range of revisions is
duplicated automatically.

Why would people want to replicate just a single change? It comes up more often
than you'd think. For example, let's go back in time and imagine that you
haven't yet merged your private feature branch back to the trunk. At the water
cooler, you get word that Fred made an interesting change to `integer.c` on the
trunk. Looking over the history of commits to the trunk, you see that in
revision 355 she fixed a critical bug that directly impacts the feature you're
working on. You might not be ready to merge all the trunk changes to your branch
just yet, but you certainly need that particular bug fix in order to continue
your work:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn diff -c 355 ^/myproject/trunk

Index: integer.c
===================================================================
--- integer.c   (revision 354)
+++ integer.c   (revision 355)
@@ -147,7 +147,7 @@
-    case 9:  sprintf(info->operating_system, "CP/MM");
+    case 9:  sprintf(info->operating_system, "CP/M"); break;

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

Just as you used **svn diff** in the prior example to examine revision 355, you
can pass the same option to **svn merge**:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge -c 355 ^/myproject/trunk
--- Merging r355 into '.':
U    integer.c
--- Recording mergeinfo for merge of r355 into '.':
U   .

$ svn status
M       integer.c
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

### Merge conflicts

Just like the **svn update** command, **svn merge** applies changes to your
working copy. And therefore it's also capable of creating conflicts. The
conflicts produced by **svn merge**, however, are sometimes different, and this
section explains those differences.

To begin with, assume that your working copy has no local edits. When you **svn
update** to a particular revision, the changes sent by the server always
apply “cleanly” to your working copy. The server produces the delta by comparing
two trees: a virtual snapshot of your working copy, and the revision tree you're
interested in. Because the left hand side of the comparison is exactly equal to
what you already have, the delta is guaranteed to correctly convert your working
copy into the right hand tree.

But **svn merge** has no such guarantees and can be much more chaotic: the
advanced user can ask the server to compare *any* two trees at all, even ones
that are unrelated to the working copy! This means there's large potential for
human error. Users will sometimes compare the wrong two trees, creating a delta
that doesn't apply cleanly. The **svn merge**subcommand does its best to apply
as much of the delta as possible, but some parts may be impossible. A common
sign that you merged the wrong delta is unexpected tree conflicts:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge -r 1288:1351 http://svn.example.com/myrepos/branch
--- Merging r1289 through r1351 into '.':
   C bar.c
   C foo.c
   C docs
--- Recording mergeinfo for merge of r1289 through r1351 into '.':
 U   .
Summary of conflicts:
  Tree conflicts: 3

$ svn st
!     C bar.c
      >   local missing, incoming edit upon merge
!     C foo.c
      >   local missing, incoming edit upon merge
!     C docs
      >   local delete, incoming edit upon merge
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

In the previous example, it might be the case that `bar.c`, `foo.c`,
and `docs` all exist in both snapshots of the branch being compared. The
resultant delta wants to change the contents of the corresponding paths in your
working copy, but those paths don't exist in the working copy. Whatever the
case, the preponderance of tree conflicts most likely means that the user
compared the wrong two trees; it's a classic sign of user error. When this
happens, it's easy to recursively revert all the changes created by the merge
(`svn revert . --recursive`), delete any unversioned files or directories left
behind after the revert, and rerun **svn merge** with the correct arguments.

Also keep in mind that a merge into a working copy with no local edits can still
produce text conflicts.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn merge -c 1701 http://svn.example.com/myrepos/branchX --accept postpone
--- Merging r1701 into '.':
C     glub.c
C     sputter.c
--- Recording mergeinfo for merge of r1701 into '.':
 U   .
Summary of conflicts:
  Text conflicts: 2

C:\SVN\src-branch-1.7.x>svn st
 M      .
?       glub.c.merge-left.r1700
?       glub.c.merge-right.r1701
C       glub.c
?       glub.c.working
?       sputter.c.merge-left.r1700
?       sputter.c.merge-right.r1701
C       sputter.c
?       sputter.c.working
Summary of conflicts:
  Text conflicts: 2
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

How can a conflict possibly happen? Again, because the user can request **svn
merge** to define and apply any old delta to the working copy, that delta may
contain textual changes that don't cleanly apply to a working file, even if the
file has no local modifications.

 

### Merges and Moves

A common desire is to refactor source code, especially in Java-based software
projects. Files and directories are shuffled around and renamed, often causing
great disruption to everyone working on the project. Sounds like a perfect case
to use a branch, doesn't it? Just create a branch, shuffle things around, and
then merge the branch back to the trunk, right?

This scenario doesn't work so well right now and is considered one of
Subversion's current weak spots. The problem is that Subversion's **svn
update** command isn't as robust as it should be, particularly when dealing with
copy and move operations.

When you use **svn copy** to duplicate a file, the repository remembers where
the new file came from, but it fails to transmit that information to the client
which is running **svn update** or **svn merge**. Instead of telling the
client, “Copy that file you already have to this new location,” it sends down an
entirely new file. This can lead to problems, especially because the same thing
happens with renamed files. A lesser-known fact about Subversion is that it
lacks “true renames”—the **svn move** command is nothing more than an
aggregation of **svn copy** and **svn delete**.

For example, suppose that while working on your private branch, you
rename `integer.c` to `whole.c`. Effectively you've created a new file in your
branch that is a copy of the original file, and deleted the original file.
Meanwhile, back on `trunk`, Fred has committed some improvements to `integer.c`.
Now you decide to merge your branch to the trunk:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ cd myproject/trunk

$ svn merge --reintegrate ^/myproject/branches/test-branch
--- Merging differences between repository URLs into '.':
D    integer.c
A    whole.c
U    .
--- Recording mergeinfo for merge between repository URLs into '.':
U   .
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 

The merge operation has deleted the latest version of the `integer.c` file (the
one containing Fred’s latest changes), and blindly added your
new `whole.c` file—which is a duplicate of the *older* version of `integer.c`.
The net effect is that merging your “rename” to the trunk has removed Fred's
recent changes from the latest revision!

 

### Traversing Branches

The **svn switch** command transforms an existing working copy to reflect a
different branch. While this command isn't strictly necessary for working with
branches, it provides a nice shortcut. In one of our earlier examples, after
creating your private branch, you checked out a fresh working copy of the new
repository directory. Instead, you can simply ask Subversion to change your
working copy of `/myproject/trunk` to mirror the new branch location:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ cd myproject

$ svn info | grep URL
URL: http://svn.example.com/repos/myproject/trunk

$ svn switch ^/myproject/branches/test-branch
U    integer.c
U    button.c
U    Makefile
Updated to revision 341.

$ svn info | grep URL
URL: http://svn.example.com/repos/myproject/branches/test-branch
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

“Switching” a working copy that has no local modifications to a different branch
results in the working copy looking just as it would if you'd done a fresh
checkout of the directory. It's usually more efficient to use this command,
because often branches differ by only a small degree. The server sends only the
minimal set of changes necessary to make your working copy reflect the branch
directory.

The **svn switch** command also takes a `--revision` (`-r`) option, so you need
not always move your working copy to the `HEAD` of the branch. Because **svn
switch** is essentially a variant of **svn update**, it shares the same
behaviors; any local modifications in your working copy are preserved when new
data arrives from the repository.

 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ svn copy http://svn.example.com/repos/myproject/trunk \
           http://svn.example.com/repos/myproject/branches/newbranch \
           -m "Create branch 'newbranch'."
Committed revision 353.
$ svn switch ^/myproject/branches/newbranch
At revision 353.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The **svn switch** command, like **svn update**, preserves your local edits. At
this point, your working copy is now a reflection of the newly created branch,
and your next** svn commit** invocation will send your changes there.

 

### Common Branching Patterns

There are many different uses for branching and **svn merge**, and this section
describes the most common.

 

### Release Branches

Most software has a typical life cycle: code, test, release, repeat. There are
two problems with this process. First, developers need to keep writing new
features while quality assurance teams take time to test supposedly stable
versions of the software. New work cannot halt while the software is tested.
Second, the team almost always needs to support older, released versions of
software; if a bug is discovered in the latest code, it most likely exists in
released versions as well, and customers will want to get that bug fix without
having to wait for a major new release.

Here's where version control can help. The typical procedure looks like this:

1.  *Developers commit all new work to the trunk.* Day-to-day changes are
    committed to `/trunk`: new features, bug fixes, and so on.

2.  *The trunk is copied to a “release” branch.* When the team thinks the
    software is ready for release (say, a 1.0 release), `/trunk` might be copied
    to `/branches/1.0`.

3.  *Teams continue to work in parallel.* One team begins rigorous testing of
    the release branch, while another team continues new work (say, for version
    2.0) on `/trunk`. If bugs are discovered in either location, fixes are
    ported back and forth as necessary. At some point, however, even that
    process stops. The branch is “frozen” for final testing right before a
    release.

4.  *The branch is tagged and released.* When testing is
    complete, `/branches/1.0` is copied to `/tags/1.0.0` as a reference
    snapshot. The tag is packaged and released to customers.

5.  *The branch is maintained over time.* While work continues on `/trunk` for
    version 2.0, bug fixes continue to be ported
    from `/trunk` to `/branches/1.0`. When enough bug fixes have accumulated,
    management may decide to do a 1.0.1 release: `/branches/1.0` is copied
    to `/tags/1.0.1`, and the tag is packaged and released.

 

![](<img/svn-release-branches.png>)

 

### Feature Branches

A *feature branch* is the sort of branch that's been in the example in this
chapter. It's a temporary branch created to work on a complex change without
interfering with the stability of `/trunk`. Unlike release branches (which may
need to be supported forever), feature branches are born, used for a while,
merged back to the trunk, and then ultimately deleted. They have a finite span
of usefulness.

 

![](<img/svn-Feature_branch.png>)

 

Again, project policies vary widely concerning exactly when it's appropriate to
create a feature branch. Some projects never use feature branches at all:
commits to `/trunk` are a free-for-all. The advantage to this system is that
it's simple—nobody needs to learn about branching or merging. The disadvantage
is that the trunk code is often unstable or unusable. Other projects use
branches to an extreme: no change is *ever* committed to the trunk directly.
Even the most trivial changes are created on a short-lived branch, carefully
reviewed, and merged to the trunk. Then the branch is deleted. This system
guarantees an exceptionally stable and usable trunk at all times, but at the
cost of tremendous process overhead.

Most projects take a middle-of-the-road approach. They commonly insist
that `/trunk` compile and pass regression tests at all times. A feature branch
is required only when a change requires a large number of destabilizing commits.
A good rule of thumb is to ask this question: if the developer worked for days
in isolation and then committed the large change all at once (so
that `/trunk` were never destabilized), would it be too large a change to
review? If the answer to that question is “yes,” the change should be developed
on a feature branch. As the developer commits incremental changes to the branch,
they can be easily reviewed by peers.

Finally, there's the issue of how to best keep a feature branch in “sync” with
the trunk as work progresses. As we mentioned earlier, there's a great risk to
working on a branch for weeks or months; trunk changes may continue to pour in,
to the point where the two lines of development differ so greatly that it may
become a nightmare trying to merge the branch back to the trunk.

This situation is best avoided by regularly merging trunk changes to the branch.
Make up a policy: once a week, merge the last week's worth of trunk changes to
the branch.

When you are eventually ready to merge the “synchronized” feature branch back to
the trunk, begin by doing a final merge of the latest trunk changes to the
branch. When that's done, the latest versions of branch and trunk are absolutely
identical except for your branch changes. You then merge back with
the `--reintegrate` option:

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
$ cd trunk-working-copy

$ svn update
Updating '.':
At revision 1910.

$ svn merge --reintegrate ^/myproject/branches/mybranch
--- Merging differences between repository URLs into '.':
U    real.c
U    integer.c
A    newdirectory
A    newdirectory/newfile
U   .
…
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

 
