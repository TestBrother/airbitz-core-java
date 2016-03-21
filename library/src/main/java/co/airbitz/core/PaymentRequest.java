/**
 * Copyright (c) 2014, Airbitz Inc
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms are permitted provided that
 * the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Redistribution or use of modified source code requires the express written
 *    permission of Airbitz Inc.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the Airbitz Project.
 */
package co.airbitz.core;

import co.airbitz.internal.Jni;
import co.airbitz.internal.core;
import co.airbitz.internal.tABC_CC;
import co.airbitz.internal.tABC_PaymentRequest;

public class PaymentRequest {
    private tABC_PaymentRequest mPaymentRequest;

    PaymentRequest(tABC_PaymentRequest request) throws AirbitzException {
        mPaymentRequest = request;
    }

    tABC_PaymentRequest coreRequest() {
        return mPaymentRequest;
    }

    public String domain() {
        return mPaymentRequest.getSzDomain();
    }

    public long amount() {
        return Jni.get64BitLongAtPtr(
            Jni.getCPtr(mPaymentRequest.getAmountSatoshi()));
    }

    public String memo() {
        return mPaymentRequest.getSzMemo();
    }

    public String merchant() {
        return mPaymentRequest.getSzMerchant();
    }

    @Override
    protected void finalize() throws Throwable {
        if (mPaymentRequest != null) {
            core.ABC_FreePaymentRequest(mPaymentRequest);
        }
        super.finalize();
    }
}