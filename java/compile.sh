cp="lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:lib/jsoup-1.6.1.jar:."
pdir="me/walkable"
options="-classpath $cp -Xlint:unchecked"
javac="/usr/bin/javac"

cd /var/git/hackers/walkable/java

$javac $options $pdir/cj/*.java
$javac $options $pdir/cj/giltcity/*.java
$javac $options $pdir/db/*.java
$javac $options $pdir/foursquare/*.java
$javac $options $pdir/groupon/*.java
$javac $options $pdir/util/*.java
$javac $options $pdir/yelp/*.java
$javac $options $pdir/*.java 

echo "Finished Compiling - Lets Run!"
echo ""
