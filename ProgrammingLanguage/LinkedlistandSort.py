# -*- coding: utf-8 -*-
import random

class RecursionLinkedList(object):

    def __init__(self):
        self.head = None

    def _link_first(self, element): # node의 앞부분에 추가 시키는 함수
        # connect newly created node the beginning of the list
        if self is not None:
            self.head = Node(element, self.head)
        else:
            self.head = Node(element, None)

    def _link_last(self, element, node): # node가 매개변수로 주어지면 주어진 node 뒤에 추가 시키는 함수
        """
        :type node: Node
        :type element: char
        """
        # assignment(1) connect given node the next of the last linked node
        if node.next is None:  # 주어진 node의 다음 node가 없으면
            node.next = Node(element, None) # element 추가
        else:  # 주어진 node의 다음 노드가 있으면
            self._link_last(element, node.next) # 다음 node를 변수로 현 함수 다시 호출
        # 결국 node를 쭉 찾아나가다가 마지막 node뒤에 추가하게 된다
    def _link_next(self, index, element): # 주어진 index 다음에 node 추가
        """
        :type index: Int
        :type element: Char
        :param index
        :param element
        """
        next = self.get_node(index).next # next에 주어진 index번째 node의 다음 node주소를 저장
        self.get_node(index).next = Node(element, next) # 주어진 index번째의 다음 node에 node연결
        # 결국 index번째의 node - element를 가진 node - 원래 index번째의 node와 연결되어있던 다음 node의 형식으로 연결된다

    def _unlink_first(self): # 첫번째 node(=head)의 element 값을 반환하면서 제거
        # unlink first node of list
        x = self.head
        """:type : Node"""
        element = x.element
        self.head = x.next
        return element

    def _unlink_next(self, pred): # pred node의 다음 node를 제거하면서 element 값 반환
        """
        :type pred: Node
        :param pred:
        """
        x = pred.next
        element = x.element
        pred.next = x.next
        return element

    def _get_node(self, index, x): # 주어진 node의 index번째 node를 반환 (보통 x는 head로 주어진다)
                                    # 따라서 그냥 index번째 node 반환으로 봐도 무방(0번째는 head)
        """
        :type index:int
        :type x:Node
        :param index:
        :param x:
        """
        # assignment(2) Get nth(index) node
        if index == 0:  # index가 0이면 현재 node 반환
            return x
        elif index > 0:  # 0보다 클 경우 index를 하나 낮추고 다음 node를 다음 node로 해서 함수 다시 호출
            return self._get_node(index-1,x.next)
        # 결국 index가 0이 될 때까지 node를 차례차례 넘어가면서 찾은 후, 반환한다
        # ex) index 3 : head(3) - node(2) - node(1) - node(0) <- 반환 ....

    def get_node(self, index):
        return self._get_node(index, self.head)

    def __len__(self): # 내가 구현한 함수를 이용해서 node의 길이를 반환하는 함수
        if self.head is None: # head가 없으면 node가 없으므로 0 반환
            return 0
        return len(self.head)

    def add(self, element, index=None): # 원하는 위치에 element를 추가 시키는 함수
        if index is None: # index를 넘겨받지 않으면
            if self.head is None: # node가 없을 때, 새로 생성해서 추가
                self._link_first(element)
            else: # 기존의 node가 있으면
                self._link_last(element, self.head) # node의 맨 뒤에 추가
            return

        if index < 0 or index > len(self): # index가 범위를 벗어나면 에러출력
            print "ERROR"
        elif index == 0: # index가 0이면 처음자리에 node추가
            self._link_first(element)
        else: # 0 이외의 수일 경우
            self._link_next(index-1, element)
            # 호출하는 함수가 넘겨주는 index다음에 node를 추가하므로 index위치에 element를 가진 node를 추가

    def remove(self, index): # index번째의 node를 제거하는 함수
        if index < 0 or index > len(self):
            print "ERROR"
        elif index == 0: # index가 0 이면
            return self._unlink_first() # 맨 앞의 node제거
        else: # index가 0이 아니면
            return self._unlink_next(self._get_node(index - 1, self.head)) # index번째 node 제거

    def __str__(self): # 내가 구현한 함수로 node의 element 출력
        if self.head is None:
            return "List is null"
        return str(self.head)

    def _reverse(self, x, pred): # 주어진 x의 다음 node를 자신의 이전 node로 바꾸어주는 함수
        """
        :type x: Node
        :type pred: Node
        :param x:
        """
        # assignment(5)
        # Fill out, Use recursion
        nexd = x.next # x의 다음 node를 nexd에 저장
        x.next = pred # x의 다음 node를 자신의 이전 node로 변경
        if nexd is None: # nexd가 없으면 결국 x가 마지막 node이므로
            self.head = x # head는 x
        else: # nexd가 있으면 x가 마지막 node가 아니므로
            self._reverse(nexd, x) # 다음 node로 넘어가며 같은 작업 반복 node의 끝까지
        # pred에 대한 다음 node 지정 작업을 해주지 않아도 되는 이유는 자연스레 재귀호출전에 작업이 완료된다
        # 이는 어디까지나 초기 매개변수 x가 head로 받아지는 경우를 가정으로 한다

    def reverse(self):
        self._reverse(self.head, None)

    def iter_selection_sort(self): # 추가 과제를 이해하기 쉽게하기위해 반복문을 사용해서 만든 코드
        # 밑에 내가 만든 selection_sort()에서 구동 원리를 설명하겠다
        current_node = self.head
        compare_node = self.head
        min_node = self.head

        while current_node.next is not None:
            while compare_node is not None:
                if min_node.element > compare_node.element:
                    min_node = compare_node
                compare_node = compare_node.next
            current_node.element, min_node.element = min_node.element, current_node.element
            # 이 부분이 굉장히 도움이 되었다. 이런 식으로 적용함으로써 각 node의 element값만 바꿀 수 있다
            current_node = current_node.next
            compare_node = current_node
            min_node = current_node

    def selection_sort(self):
        self._selection(self.head)

    # Bonus Assignment
    def _selection(self, current_node): # current_node와 최소값을 가진 node의 element를 바꿔주는 함수
    # Fill out, Use recursion
        min_node = self.compare(current_node, current_node, None) # 최소값을 가진 node를 찾아서 min_node에 저장

        min_node.element, current_node.element = current_node.element, min_node.element
        # current_node와 최소값을 가진 node의 element를 바꿈
        if current_node.next is not None: # 모든 node를 바꾸어 줬으면 재귀를 종료한다
            self._selection(current_node.next) # 다음 node로 옮겨가며 반복







    def compare(self, current_node, compare_node, min_node):
        # current_node와 다음 node들을 비교해가며 최소값을 가진 node를 찾아서 반환하는 함수
    # Fill out, Use recursion
        min_node = current_node # 일단 최소값을 가진 node를 current_node로 저장
        if compare_node is None: # 비교할 대상이 없으면
            return min_node # 지금 가진 min_node 반환
        elif int(current_node.element) > int(compare_node.element): # current_node값이 비교 대상보다 크면
            min_node = compare_node # min_node에 '그' node를 저장
            return self.compare(min_node, compare_node.next, None) # '그' node를 기점으로 다시 비교
        else: # current_node값이 비교대상보다 여전히 작으면
            return self.compare(min_node, compare_node.next, None) # 그냥 다음 node와 계속 비교

class Node(object): # node의 정보를 담은 class
    """
    :type element:char
    :type next: Node
    """

    def __init__(self, element, next):
        """
        :type element : char
        :type next : Node
        """
        self.element = element
        self.next = next

    def __str__(self): # node의 element값을 차례로 반환
        # assignment(3)
        if self.next is None:  # 다음 node가 없으면
            return str(self.element) # 현재 node 출력 반환
        else: # 다음 node가 있으면
            return str(self.element) + " " + str(self.next.__str__()) # 현재 node의 값과 다음 node의 출력값 반환
        # int형과 char형 등을 모두 호환하기위해 string형으로 변환하여 반환했다

    def __len__(self): # node의 길이를 반환하는 함수
        # assignment(4) Return size of entire node
        if self.next is not None: # 다음 node가 존재하면 1을 더해준 후, 함수 호출
            return 1 + self.next.__len__()
        else: # 다음 node가 없으면 1반환
            return 1
    # 결국 node가 있을 때마다 1씩 더해줘서 길이를 반환하는 결과를 갖게 됨

def test_recursion_linked_list():
    INPUT = ['a', 'b', 'c', 'd', 'e']
    test_RLL = RecursionLinkedList()
    for i in INPUT:
        test_RLL.add(i)
    print str(test_RLL)
    print "List size is " + str(len(test_RLL))
    test_RLL.add('z', 0)
    print "List size is " + str(len(test_RLL))
    print str(test_RLL)
    print test_RLL.get_node(4).element
    print test_RLL.remove(0)
    print str(test_RLL)
    test_RLL.reverse()
    print str(test_RLL)

def test_selection_sort():
    random_numbers=[]
    for i in range(10):
        random_numbers.append(random.randrange(0, 100))

    test_RSS = RecursionLinkedList()
    for i in random_numbers:
        test_RSS.add(i)
    print "List size is " + str(len(test_RSS))
    print str(test_RSS)
    #test_RLL.iter_selection_sort()
    test_RSS.selection_sort()
    print str(test_RSS)


test_recursion_linked_list()
test_selection_sort()
