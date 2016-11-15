# stacktrace-anonymous

http://www.programbuddy.com/index.php/2016/10/09/anonymize-thread-dump/

## run

docker run --rm -i programbuddy/threaddump-anonymous < threaddump_input.txt > threaddump_output.txt

## run and get a map of replaced text and new text

docker run --rm -i -v $PATH_TO_FILE/mapfile.txt:/threaddump-anonymous-master/target/out_anonymous_map.txt programbuddy/threaddump-anonymous < threaddump_input.txt > threaddump_output.txt
