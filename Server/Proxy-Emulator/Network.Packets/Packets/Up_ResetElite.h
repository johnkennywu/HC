#include "pb\up.pb.h"
#include <iostream>

#pragma once

using namespace System;
using namespace System::Collections::Generic;

namespace Network
{
	namespace Packets
	{
		// _reset_elite, 22
		public ref struct Up_ResetElite : Up_UpMsg
		{
			Up_ResetElite()
			{
				MessageType = 22;
			}

			virtual String^ ToString() override
			{
				return "";
			}
		};
	}
}