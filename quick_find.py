#!/usr/bin/env python

class QuickFind:

    def __init__(self, n):
        self.id = []

        for i in range(n):
            self.id.append(i)

    
    def find(self, p, q):
        return self.id[p] == self.id[q]

    
    def unite(self, p, q):
        pid = self.id[p]

        for n,i in enumerate(self.id):
            if i == pid:
                self.id[n] = self.id[q]


    def print_array(self):
        print self.id


qf = QuickFind(10)

qf.unite(9,7)
qf.unite(5,1)
qf.unite(7,0)
qf.unite(8,7)
qf.unite(7,6)
qf.unite(1,9)

qf.print_array();
