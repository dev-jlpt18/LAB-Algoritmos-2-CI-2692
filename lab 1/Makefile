
KC = kotlinc

KFLAGS = -include-runtime

PROG = TestSort.jar

SRC = Sortlib.kt Main.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)
.PHONY : clean

clean :
	rm -rf $(PROG)
