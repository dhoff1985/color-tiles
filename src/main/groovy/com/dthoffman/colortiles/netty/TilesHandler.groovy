package com.dthoffman.colortiles.netty

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.UnpooledByteBufAllocator
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.HttpMessage
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.handler.codec.http.HttpVersion

import java.nio.CharBuffer
import java.nio.charset.Charset

/**
 * Created by dhoffman on 1/28/16.
 */
class TilesHandler extends ChannelInboundHandlerAdapter {
    @Override
    void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg)
        if(msg instanceof HttpMessage) {
            println msg.uri
            ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, toBuffer("bar")))
            ctx.flush()
            ctx.close()
        }
    }

    ByteBuf toBuffer(String out) {
        ByteBufUtil.encodeString(UnpooledByteBufAllocator.DEFAULT, CharBuffer.wrap(out.toCharArray()), Charset.forName("UTF-8"))
    }
}
