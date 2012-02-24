cp="lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:."
pdir="me/walkable"
options="-classpath $cp -Xlint:unchecked"

javac $options $pdir/yelp/YelpCategory.java
javac $options $pdir/db/Location.java
javac $options $pdir/db/Deal.java
javac $options $pdir/ApiCall.java
javac $options $pdir/groupon/GrouponParse.java
java -classpath $cp $pdir/ApiCall

#javac -classpath lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:. Location.java
#javac -classpath lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:. YelpParse.java
#javac -classpath lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:. ApiCall.java
#java  -classpath lib/scribe-1.3.0.jar:lib/commons-codec-1.6.jar:lib/gson-2.1.jar:lib/mysql-connector-java-5.1.18-bin.jar:. ApiCall 
