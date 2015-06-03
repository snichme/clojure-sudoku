# Sudoku Solver in Clojure

## Installation
* First: Clone the repo
* Second: Install needed software (see below)

### Locally
```
Install Java 8 if you don't have it already
Install Leiningen http://leiningen.org/#install (or "brew home leiningen")
```
### With Vagrant

In case you dont want to install the software above or
don't want to replace your current Java version you can run it with Vagrant.

```
Step into the project
Run "vagrant up"
When that finishes, run "vagrant ssh" to access the machine
The source is available at /vagrant on your virtual machine
```

## Running the code

```
cd <your-project-root>
lein midje
```
This command will run all the tests and thats all we can do with this project :)

There should be 30 failing tests and 6 passed.
