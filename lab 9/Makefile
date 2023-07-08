KC = kotlinc

KFLAGS = -include-runtime

PROG = TestMorse.jar

SRC = ArbolBinarioMorse.kt CodigoMorse.kt PruebaCodigoMorse.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)
.PHONY : clean

clean :
	rm -rf $(PROG)
