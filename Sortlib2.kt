fun swap(A: Array<Int>, i: Int, j: Int){
	var tmp = A[i]
	A[i] = A[j]
	A[j] = tmp
}

fun insertionSort(A: Array<Int>) {
	for (i in 1 until A.size) {
		var j = i
		while (A[j] < A[j-1]) {
			swap(A, j, j-1)
			j = j-1
			if (j == 0) {
				break
			}
		}
	}
}

fun selectionSort(A: Array<Int>) {
	for (i in 0 until (A.size-1)) {
		var tmpInd = i
		var tmp = A[i]
		for (j in (i+1) until A.size) {
			if (A[j] < tmp) {
				tmp = A[j]
				tmpInd = j
			}
		}
		swap(A, i, tmpInd)
	}
}

fun shellsort(A: Array<Int>) {
	var incr = (A.size).div(2)
	while (incr > 0) {
		for (i in (incr+1) until A.size) {
			var j = i - incr
			while (j > -1) {
				if (A[j] > A[j+incr]) {
					swap(A, j, j+incr)
					j = j - incr
				} else {
					j = -1
				}
			}
		}
		incr = incr.div(2)
	}
}

fun bubblesort(A: Array<Int>) {
	for (i in 0 until (A.size-1)) {
		for (j in (A.size - 1) downTo (i+1)) {
			if (A[j] < A[j-1]){
				swap(A, j, j-1)
			}
		}
	}
}

