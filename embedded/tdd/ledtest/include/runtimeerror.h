#ifndef RUNTIME_ERROR_H
#define RUNTIME_ERROR_H

void RuntimeError(const char * message, int parameter,
		  const char * file, int line);

#define RUNTIME_ERROR(description, parameter)\
	RuntimeError(description, parameter, __FILE__, __LINE__)

void RuntimeError_Reset(void);
const char * RuntimeError_GetLastError(void);
int RuntimeError_GetLastParameter(void);

#endif