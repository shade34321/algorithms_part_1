#!/usr/bin/env python

class QuickUnion:

    def __init__(self, n):
        self.id = []
        self.size = []

        for i in range(n):
            self.id.append(i)
            self.size.append(1)

    def root(self, i):
        while i != self.id[i]:
            i = self.id[i]

        return i

    def find(self, p, q):
        return self.root(p) == self.root(q)

    def unite(self, p, q):
        i = self.root(p)
        j = self.root(q)

        if i == j:
            return
        elif self.size[i] < self.size[j]:
            self.id[i] = j
            self.size[j] += self.size[i]
        else:
            self.id[j] = i
            self.size[i] += self.size[j]

    def print_id(self):
        print self.id



qu = QuickUnion(10)
qu.print_id()

qu.unite(1,2)
qu.unite(9,3)
qu.unite(0,4)
qu.unite(5,9)
qu.unite(0,2)
qu.unite(9,8)
qu.unite(8,6)
qu.unite(5,0)
qu.unite(3,7)

qu.print_id()
