cd user/share_linux
name=$(lsof -i:9901|tail -1|awk '"$1"!=""{print $2}')
if [ -z $name ]
then
        echo "No process can be used to killed!"
        java -jar *.jar >log.file  2>&1 &
        exit 0
fi
id=$(lsof -i:9901|tail -1|awk '"$1"!=""{print $2}')
kill -9 $id
echo "Process name=$name($id) kill!"
java -jar *.jar >log.file 2>&1 &
ls
exit




