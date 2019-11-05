;;; junit.el --- Set up Junit Tests

;; $Id: junit.el,v 1.1.1.1 2001/06/11 02:46:50 simonc Exp $
;; Copyright (C) 2001 Simon Crase

;; Author: Simon A. Crase <simon_crase@bigpond.com >
;; Created: $Date: 2001/06/11 02:46:50 $
;; Last version number: $Revision: 1.1.1.1 $

;; Keywords: extensions, languages, tools

;; Junit.el is free software; you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation; either version 2, or (at your option)
;; any later version.

;; Junit.el is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with GNU Emacs; see the file COPYING.  If not, write to the
;; Free Software Foundation, Inc., 59 Temple Place - Suite 330,
;; Boston, MA 02111-1307, USA.

;;; Commentary:

;; This is the start of a minor mode for setting up unit tests using
;; junit - http://www.junit.org.
;;
;; Place this file somewhere like /usr/local/emacs, set up load-path and
;; load this library .e.g. 
;;
;;(setq load-path
;;      (append (list "/usr/local/emacs")
;;	      load-path))
;;
;;(load-library "junit")
;;
;;
;;
;;  \C-ca  junit-test-case           Create a new test case file
;;  \C-cb  junit-test                Create a new test within file
;;  \C-cq  junit-test-assert-equals  Add assertEquals to current test
;;  \C-cr  junit-test-assert-true    Add assertTrue to current test
;;  \C-cs  junit-add-test-suite      Add subordinate test suite to this suite.
;;  \C-ct  junit-add-test-to-suite   Add test case to suite.
;;  \C-cx  junit-extract-class       Extract inner class to new file.
;;
;;
;;  junit.el requires tempo.el, which should have come with your
;;  copy of emacs.  Otherwise try
;;  David Kågedal <davidk@lysator.liu.se>
;;
;;; Known bugs:

;; Class comments are not being indented properly.

;; Key mappings do not currently follow the standards for an emacs
;; minor mode - so they arelikely to change soon.

;; junit-test-assert-true needs to ne modified to do a plain assert
;; for junit versions prior to 3.7.

;; 'junit-mode-hook
;; 'junit-mode-on-hook
;; 'junit-mode-off-hook

;; Contributors:

;; 
;; 
;;; Code:

;; 

;;; User options

(require 'easy-mmode)

(require 'tempo)

(defvar
  junit-version
  "3.7"
  "Version number for junit.  Used to set stuff that is version specific.")

;;; Set up a template for a junit test case
;;; You will be prompted for package name and classname.
;;; To create a class FooTest, open a new file, enter
;;; junit mode, then run this command.  When promted for the class,
;;; type Foo.  You will get a testcase TestFoo, including an inner
;;; class Foo.  I generally start a new class this way - I write
;;; the test case FooTest, then create an inner class Foo, and get
;;; Foo to work before splitting it out into its own file Foo.java.
;;; See junit-tst below for adding tests.

(defconst junit-sep "===" "Used to mark inner class.")

;; junit-test-util
;;
;; This is a wrapper around tempo.  It creates a template, applies it and
;; indents the file.

(defun junit-test-util (name elements tag)
  (let ((template (tempo-define-template name elements tag))
	(old-tempo-interactive tempo-interactive))
    (setq tempo-interactive t)
    (apply template nil)
    (indent-region (point-min) (point-max) nil)
    (setq tempo-interactive old-tempo-interactive)))

(defun junit-test-case ()
  "Create a test case, including an inner class.
   The idea is that the inner class is the one under
   test, and it will be split out into a separate file
   once it passes all tests."
  (interactive)
  (junit-test-util "j-junit"
		   '("package "
		     (p "Package: ") ";" n>
		     "import junit.framework.*;" n>
		     "import junit.extensions.*;" n>
		     n>
		     "/**" n>
		     " * This test case ..." n>
		     " *" n>
		     " */" n>
		     (p "Class Name: " className NOINSERT)
		     "public class " (s className)
		     "Test extends TestCase {" n>
		     "	public " (s className) "Test (String name) {" n>
		     "		super(name);" n>
		     "	}" n>
		     "	public static void main (String[] args) {" n>
		     "		junit.textui.TestRunner.run (suite());" n>
		     "	}" n>
		     "	protected void setUp() {" n>
		     "        the" (s className) "=new "
		     (s className) "();" n>
		     "	}" n>
		     "	public static Test suite() {" n>
		     "		return new TestSuite(" (s className)
		     "Test.class);" n>
		     "	}" n>
		     n>
		     "// ADD TESTS HERE:"
		     n>
		     n>
		     "	public void tearDown() {" n>
		     "        }" n>
		     n>
		     "// " junit-sep n>
		     n>
		     "/**" n>
		     " * This class ..." n>
		     " *" n>
		     " */" n>
		     "public class " (s className) "{" n>
		     "}" n> n
		     (s className) " the" (s className) ";" n>
		     "}" n> n>
		     "/**" n>
		     "* Local "
		     "Variables:" n>
		     " * mode: junit " n>
		     " * End:" n>
		     "*/" n>
		     )
		   "junit"))

;;; Set up a template for a single junit test
;;; Takes one parameter and uses it to create test function ,e.g.
;;; Frobnicate->testFrobnicate

(defun junit-test ()
  "Create a test.  Typically take a name and add 'test' to the front.
  E.g. Frobnicate becomes testFrobnicate."
  (interactive)
  (junit-test-util "j-junit-test"
		   '("/**" n>
		     " * This tests ..." n>
		     " *" n>
		     " */" n>
		     "	public void test"
		     (p "Test Name: ")
		     "() {" n>
		     "// TO DO:" n>
		     n>
		     "	}" n>
		     n>
		     )
		   "junit-test"))
;;;  AssertEquals

(defun junit-test-assert-equals ()
  "Build an assertEquals"
  (interactive)
  (junit-test-util "j-junit-assert-equals"
		   '("assertEquals("
		     (p "Expected value: " )
		     ", "
		     (p "Expression: " )
		     ");" n>
		     )
		   "junit-assert-equals"))

;;;  AssertTrue

;;  junit-calculate-assert-true
;;
;;  Check to see whether we should use assert or assertTrue.
;;
(defun junit-calculate-assert-true()
  (if (string-lessp "3.6" junit-version)
      "assertTrue"
    "assert"))


(defun junit-test-assert-true ()
  "Build an assertTrue (assert pre junit 3.7."
  (interactive)
  (junit-test-util "j-junit-assert-true"
		   '((junit-calculate-assert-true) "("
		     (p "Expression: " )
		     ");" n>
		     )
		   "junit-assert-true"))

(defun junit-add-test-to-suite()
  "Add a single test to the current test suite."
  (interactive)
  (junit-test-util "j-junit-add-test-to-suite"
		   '("suite.addTest(new TestSuite("
		     (p "Test name: " )
		     ".class));" n>
		     )
		   "junit-add-test-to-suite"))


(defun junit-add-test-suite()
  "Add a subordinate test suite to the current suite." 
  (interactive)
  (junit-test-util "j-junit-add-test-suite"
		   '("suite.addTest("
		     (p "Test name: " )
		     ".suite());" n>
		     )
		   "junit-add-test-suite"))

;;  junit-get-class-name
;;
;;  Used by junit-extract-class to determine name of inner class,
;;  so class can be split into a suitable file.
;;
(defun junit-get-class-name ()
  (save-excursion
    (goto-char (point-min))
    (re-search-forward "class +\\([A-Za-z0-9]+\\)Test")
    (match-string 1)))

(defun junit-extract-class ()
  "Pull inner class out into new file.
   This is used when you are sufficiently happy with the class under
   test to give it its own file."
  (interactive)
  (save-excursion
    (let* ((file-name (concat (junit-get-class-name) ".java"))
	   (from-buffer (current-buffer))
	   (to-buffer (find-file-noselect file-name)))
      (goto-char (point-min))
      (re-search-forward "package.*;")
      (copy-region-as-kill (point-min) (point))
      (set-buffer to-buffer)
      (insert (car kill-ring-yank-pointer))
      (switch-to-buffer from-buffer)
      (re-search-forward junit-sep)
      (let ((class-start (point)))
	(push-mark)
	(forward-list)
	(copy-region-as-kill class-start (point))
	(delete-region class-start (point))
	(switch-to-buffer to-buffer)
	(insert (car kill-ring-yank-pointer))
	(indent-region  (point-min) (point-max) nil)))))

;;; Set up minor mode.

(easy-mmode-define-minor-mode junit-mode
			      ""
			      nil
			      " Junit"
			      '(("\C-ca" . junit-test-case)
				("\C-cb" . junit-test)
				("\C-cq" . junit-test-assert-equals)
				("\C-cr" . junit-test-assert-true)
				("\C-cs" . junit-add-test-suite)
				("\C-ct" . junit-add-test-to-suite)
				("\C-cx" . junit-extract-class)))

(provide 'junit-mode)

;;; $Log: junit.el,v $
;;; Revision 1.1.1.1  2001/06/11 02:46:50  simonc
;;; Baseline code.
;;;
;;;
;;;
;;; junit.el ends here

