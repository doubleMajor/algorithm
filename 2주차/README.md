# algorithm
알고리즘 공부

과제 
1. 기본큐 원형큐 리스트큐 직접 구현
2. https://school.programmers.co.kr/learn/courses/30/lessons/42587
3. https://leetcode.com/problems/time-needed-to-buy-tickets/

   n 명의 사람이 티켓을 사려고 줄을 설 때 index 0 번의 사람이 제일 앞에 서있는 사람이고 (n-1)명의 사람이 그 뒤로 줄을 서있는 상황이다
   0-indexed 숫자배열 tickets 가 주어지고 i번째 사람은 tickets[i] 값 만큼 티켓을 구매한다고 한다
   각 사람은 티켓을 구매할 때 한번에 한장씩만 살 수 있고 한장 구매에 1초가 걸린다고 한다.
   티켓을 한장 구매하고도 더 구매해야하는 사람이면 줄의 맨뒤로 돌아가는 상황이고 티켓을 다 구매한 사람은 줄을 더이상 서지 않고 떠난다고 한다
   이러한 티켓팅 상황에서 index k의 사람이 티켓을 전부 구매하는데 걸리는 시간을 구하는 문제이다