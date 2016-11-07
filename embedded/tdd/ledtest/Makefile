PATHUI = unity/include
PATHUS = unity/src
PATHI  = include
PATHS  = src
PATHT  = test
PATHB  = build
PATHD  = build/depends
PATHO  = build/objs
PATHR  = build/results

CC=gcc
DEPEND=gcc -MM -MG
CFLAGS=-I. -I$(PATHUI) -I$(PATHI) -std=c99 -Wall -Wextra -pedantic

TARGET=$(PATHB)/leddrivertest

SRCS = $(PATHS)/leddriver.c \
	$(PATHS)/runtimeerror.c \
	$(PATHUS)/unity.c \
	$(PATHUS)/unity_fixture.c \
	$(PATHT)/leddrivertest.c

OBJS = $(SRCS:.c=.o)
DEPENDS = $(SRCS:.c=.d)

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

$(TARGET): $(OBJS)
	mkdir -p $(PATHD)
	mkdir -p $(PATHO)
	mkdir -p $(PATHR)
	$(CC) -o $(TARGET) $(OBJS)

.PHONY: clean test
clean:
	-rm -f $(PATHO)/*.o
	-rm -f $(OBJS) $(DEPENDS)
	-rm -f $(TARGET)

test: $(TARGET)
	./$(TARGET) -v

%.d: %.c
	$(DEPEND) $(CFLAGS) $*.c > $*.d

-include $(DEPENDS)

