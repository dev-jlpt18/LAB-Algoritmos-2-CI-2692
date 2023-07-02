# Realizar!
KC = kotlinc

KFLAGS = -include-runtime

PROG = Test.jar

SRC = Main.kt CircularList.kt HashTableChaining.kt HashTableEntry.kt CuckooHashTable.kt CuckooHashTableEntry.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)
.PHONY : clean

clean :
	rm -rf $(PROG)
