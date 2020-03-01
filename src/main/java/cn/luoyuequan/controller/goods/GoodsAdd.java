package cn.luoyuequan.controller.goods;

import cn.luoyuequan.model.GoodsModel;
import cn.luoyuequan.service.GoodsService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class GoodsAdd extends HttpServlet {
    private String filePath;

    @Override
    public void init() {
        // 获取文件将被存储的位置
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        GoodsModel goodsModel = new GoodsModel();
        List items = null;
        try {
            items = upload.parseRequest(req);
            for (Object o : items) {
                FileItem item = (FileItem) o;

                if (item.isFormField()) {
//                System.out.println("表单参数名：" + item.getFieldName() + ",表单参数值：" + item.getString("UTF-8"));
                    goodsModel.setAttributes(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    if (item.getName() != null && !item.getName().equals("")) {
//                        System.out.println("上传文件的大小：" + item.getSize());
//                        System.out.println("文件的类型：" + item.getContentType());
//                        System.out.println("上传文件的名称：" + item.getName());// 返回上传文件在客户端的完整路径名称
                        UUID uuid = UUID.randomUUID();
                        long time = System.currentTimeMillis();
                        String fileType = item.getContentType();
                        String newFileName = time + "-" + uuid + "." + fileType.substring(fileType.indexOf("/") + 1);

                        File file = new File(filePath + newFileName);
//                        File file = new File(newFileName);
                        System.out.println(file.getAbsolutePath());

                        FileOutputStream out = new FileOutputStream(file);
                        //获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        //创建一个缓冲区
                        byte[] buffer = new byte[1024];
                        // 判断输入流中的数据是否已经读完的标识
                        int len = in.read(buffer);
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while (len > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                            out.write(buffer, 0, len);
                            out.flush();
                            len = in.read(buffer);

                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除处理文件上传时生成的临时文件
                        item.delete();

                        goodsModel.setAttributes("goods_img", newFileName);
                    }
                }
            }
        } catch (IOException | FileUploadException e) {
            e.printStackTrace();
        }
        GoodsService goodsService = new GoodsService();
        String returnData = goodsService.insertGoods(goodsModel);
        resp.getWriter().print(returnData);
    }
}
