package oupson.apng.utils

import java.util.*

class Utils {
    companion object {
        /**
         * @return True if is a png
         */
        fun isPng(byteArray: ByteArray): Boolean {
            return byteArray.copyOfRange(0, 8).contentToString() == pngSignature.contentToString()
        }

        /**
         * Know if file is an APNG
         * @return True if is an APNG
         */
        fun isApng(byteArray: ByteArray) : Boolean {
            if (!isPng(byteArray)) return false
            try {
                val acTL = byteArrayOf(0x61, 0x63, 0x54, 0x4c)
                @Suppress("LocalVariableName") val IDAT = byteArrayOf(0x49, 0x44, 0x41, 0x54)
                for (i in 0 until byteArray.size) {
                    val it = byteArray.copyOfRange(i, i + 4)
                    // if byteArray contain acTL
                    if (it.contentEquals(acTL)) {
                        return true
                    } else if (it.contentEquals(IDAT)){
                        return false
                    }
                }
                return false
            } catch (e : Exception) {
                return false
            }
        }

        /**
         * Signature for png / APNG files
         */
        val pngSignature: ByteArray = byteArrayOf(0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(), 0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte())

        enum class DisposeOp {
            APNG_DISPOSE_OP_NONE,
            APNG_DISPOSE_OP_BACKGROUND,
            APNG_DISPOSE_OP_PREVIOUS
        }

        /**
         * Get the int equivalent to the DisposeOp
         * @param disposeOp The DisposeOp
         * @return An int equivalent to the DisposeOp
         */
        fun getDisposeOp(disposeOp: DisposeOp) : Int {
            return when(disposeOp) {
                Companion.DisposeOp.APNG_DISPOSE_OP_NONE -> 0
                Companion.DisposeOp.APNG_DISPOSE_OP_BACKGROUND -> 1
                Companion.DisposeOp.APNG_DISPOSE_OP_PREVIOUS -> 2
            }
        }

        /**
         * Get the DisposeOp enum equivalent to the int
         * @param int Int of the DisposeOp
         * @return A DisposeOp
         */
        fun getDisposeOp(int: Int) : DisposeOp {
            return when(int) {
                0 -> DisposeOp.APNG_DISPOSE_OP_NONE
                1 -> DisposeOp.APNG_DISPOSE_OP_BACKGROUND
                2 -> DisposeOp.APNG_DISPOSE_OP_PREVIOUS
                else -> DisposeOp.APNG_DISPOSE_OP_NONE
            }
        }

        enum class BlendOp {
            APNG_BLEND_OP_SOURCE,
            APNG_BLEND_OP_OVER
        }

        /**
         * Get the int equivalent to the BlendOp
         * @param blendOp The BlendOp
         * @return An int equivalent to the BlendOp
         */
        fun getBlendOp(blendOp: BlendOp) : Int {
            return when(blendOp) {
                Companion.BlendOp.APNG_BLEND_OP_SOURCE -> 0
                Companion.BlendOp.APNG_BLEND_OP_OVER -> 1
            }
        }

        /**
         * Get the BlendOp enum equivalent to the int
         * @param int Int of the BlendOp
         * @return A BlendOp
         */
        fun getBlendOp(int : Int) : BlendOp {
            return when(int) {
                0 -> BlendOp.APNG_BLEND_OP_SOURCE
                1 -> BlendOp.APNG_BLEND_OP_OVER
                else -> BlendOp.APNG_BLEND_OP_SOURCE
            }
        }

        /**
         * Generate a 4 bytes array from an Int
         * @param i The int
         * @return 2 Bytes
         */
        fun to4Bytes(i: Int): ByteArray {
            val result = ByteArray(4)
            result[0] = (i shr 24).toByte()
            result[1] = (i shr 16).toByte()
            result[2] = (i shr 8).toByte()
            result[3] = i /*>> 0*/.toByte()
            return result
        }

        /**
         * Generate a 2 bytes array from an Int
         * @param i The int
         * @return 2 Bytes
         */
        fun to2Bytes(i: Int): ByteArray {
            val result = ByteArray(2)
            result[0] = (i shr 8).toByte()
            result[1] = i /*>> 0*/.toByte()
            return result
        }

        fun parseLength(byteArray: ByteArray) : Int {
            var lengthString = ""
            byteArray.forEach {
                lengthString += String.format("%02x", it)
            }
            return lengthString.toLong(16).toInt()
        }

        val fcTL : String = Arrays.toString(byteArrayOf(0x66, 0x63, 0x54, 0x4c))
        val IEND : String = Arrays.toString(byteArrayOf(0x49, 0x45, 0x4e, 0x44))
        val IDAT : String = Arrays.toString(byteArrayOf(0x49, 0x44, 0x41, 0x54))
        val fdAT : String = Arrays.toString(byteArrayOf(0x66, 0x64, 0x41, 0x54))
        val plte : String = Arrays.toString(byteArrayOf(0x50, 0x4c, 0x54, 0x45))
        val tnrs : String = Arrays.toString(byteArrayOf(0x74, 0x52, 0x4e, 0x53))
        val IHDR : String = Arrays.toString(byteArrayOf(0x49, 0x48, 0x44, 0x52))
    }
}