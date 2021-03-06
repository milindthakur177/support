/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.lite.support.common.ops;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/** Tests of {@link DequantizeOp}. */
@RunWith(RobolectricTestRunner.class)
public final class DequantizeOpTest {

  @Test
  public void dequantizeShouldSucess() {
    int[] originalData = new int[] {191, 159, 63, 127, 255, 0};
    DequantizeOp op = new DequantizeOp(127.0f, 1.0f / 128);
    TensorBuffer input = TensorBuffer.createFixedSize(new int[] {6}, DataType.UINT8);
    input.loadArray(originalData);
    TensorBuffer dequantized = op.apply(input);
    assertThat(dequantized.getDataType()).isEqualTo(DataType.FLOAT32);
    assertThat(dequantized.getFloatArray())
        .isEqualTo(new float[] {0.5f, 0.25f, -0.5f, 0, 1, -0.9921875f});
  }
}
