
KC = kotlinc

KFLAGS = -include-runtime

PROG = MergeSort.jar

SRC = Sortlib.kt PruebaMergesort.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)
.PHONY : clean

clean :
	rm -rf $(PROG)
