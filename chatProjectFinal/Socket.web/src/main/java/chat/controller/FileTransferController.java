package chat.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import chat.domain.FileInfo;
import chat.download.FileDownloadQueue;
import chat.service.FileInfoService;

@Controller
@RequestMapping("/file")
public class FileTransferController {

	private String path = "c:/java142/datacenter/resources/";

	@Autowired
	private FileInfoService fileInfoService;

	@RequestMapping("/download.do")
	public ModelAndView fileDownloader(@RequestParam("fileName") String fileName,
			@RequestParam("loginId") String loginId, @RequestParam("roomNumber") String roomNumber)
			throws InterruptedException {


		FileInfo fileInfo = new FileInfo();
		fileInfo = fileInfoService.retrieveFileByModifyName(fileName);
		fileInfo.setDownloader(loginId);

		System.out.println(fileInfo.fileInfoToString());

		File file = new File(path + fileName);

		if (!file.isFile()) {

			FileDownloadQueue.getFileInfoQueue().put(fileInfo);
		}

		while (true) {

			if (file.isFile()) {


				if (((int) file.length()) >= Integer.parseInt(fileInfo.getFileSize())) {
					break;
				}
				Thread.sleep(1000);
			}
		}

		return new ModelAndView("download", "downloadFile", file);
	}

	@RequestMapping("/fileList.do")
	public ModelAndView fileList(HttpSession session
			, String roomNumber, String loginId) {

		List<FileInfo> files = fileInfoService.retrieveFileAll(roomNumber);

		for (FileInfo f : files) {
			System.out.println(f.getFileName() + " " + f.getUploader() + " " + f.getRealFileName());
			System.out.println(roomNumber + " " + loginId);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("files", files);
		mav.addObject("loginId", loginId);
		mav.addObject("roomNumber", roomNumber);
		// 파일 넘기고 다운로드 설정하자.
		mav.setViewName("/views/fileList.jsp");
		
		return mav;
	}

}