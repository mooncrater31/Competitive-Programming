from multiprocessing import Pool

def f(x):
	return [x,x+1,x+2] 
if __name__=='__main__':
	p = Pool(5)
	print(p.map(f,[1,2,3]))
