

/**
	Auto generated by xproto.exe
*/


/*::STDAFX_H::*/
#include "xproto.h"
#include <stdio.h>
#ifdef WIN32
#include <Windows.h>
#endif


/*::PRIVATE_IMPLEMENTS::*/

void _bean_issue_error(INT x)
{
	printf("_bean_issue_error %X\n",x);
}

long _auto_pointer_class_base::AddRef()
{
	return InterlockedIncrement((long *)&m_refcount);
}

long _auto_pointer_class_base::Release()
{
	 long result =InterlockedDecrement((long *)&m_refcount);

	 if(result==0)
	 {
		delete this;
	 }
	 return result;
}
