package com.yr.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yr.entity.Page;
import com.yr.entity.School;
import com.yr.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yr.service.SchoolService;
import com.yr.service.StudentService;

@SessionAttributes(value = {"locale"})
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private SchoolService schoolService;


	/**
	 * 查询所有学校
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/school")
	public List<School> list(){
		return schoolService.query();
	}

	/**
	 *查询所有 带分页
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stulist",method = RequestMethod.GET)
	public Page<Student> query(Page<Student> page) {
		studentService.getList(page);
		return page;
	}


	/**
	 * 添加学生
	 D	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Student add(@RequestParam("file") MultipartFile file,Student student) throws Exception {

		String prefix = UUID.randomUUID().toString();
		prefix = prefix.replace("-", "");

		String fileName = prefix + "_" + file.getOriginalFilename();// 浣跨敤UUID鍔犲墠缂�鍛藉悕鏂囦欢锛岄槻姝㈠悕瀛楅噸澶嶈瑕嗙洊
		// 输入流
		InputStream inputStream = file.getInputStream();

		// 输出流
		OutputStream out = new FileOutputStream(
				new File("E:\\eclipse-4.11\\spring\\SpringCrud\\WebContent\\files\\" + fileName));

		byte b[] = new byte[1024 * 1024 * 10];
		int i = inputStream.read(b);
		while (i != -1) {
			out.write(b, 0, i);
			i = inputStream.read(b);
		}
		out.close();
		inputStream.close();
		// 把文件放入head
		student.setHead(fileName);

		int id = studentService.save(student);

		return studentService.getStudentById(id);

	}

	/**
	 * 鍒犻櫎瀛︾敓
	 * @param id
	 * @return 鍒楄〃
	 */
	@ResponseBody
	@RequestMapping(value = "/stu/{id}", method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
	public String delete(@PathVariable("id") Integer id) {
		studentService.delete(id);
		return "鍒犻櫎鎴愬姛";
	}

	/**
	 * 淇敼鍥炴樉
	 * @return 淇敼椤甸潰
	 */
	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable("id") Integer id) {
		// 鏌ュ嚭鎵�鏈夌殑瀛︽牎
		schoolService.query();
		// 鏍规嵁id鏌ヨ
		Student student= studentService.getStudentById(id);
		return student;
	}


	/**
	 * 淇敼鏁版嵁
	 * @param stu
	 * @return 鍒楄〃
	 */
	@ResponseBody
	@RequestMapping(value = "/updates", method = RequestMethod.PUT)
	public  Student update(@RequestParam("file") MultipartFile file,Student stu) throws Exception {

		String prefix = UUID.randomUUID().toString();
		prefix = prefix.replace("-", "");
		String fileName = prefix + "_" + file.getOriginalFilename();// 浣跨敤UUID鍔犲墠缂�鍛藉悕鏂囦欢锛岄槻姝㈠悕瀛楅噸澶嶈瑕嗙洊
		// 杈撳叆娴侊紝涓婁紶鏂囦欢鐨�
		InputStream inputStream = file.getInputStream();

		// 杈撳嚭娴�,淇濆瓨鏂囦欢鐨�
		OutputStream out = new FileOutputStream(
				new File("E:\\eclipse-4.11\\spring\\SpringCrud\\WebContent\\files\\" + fileName));

		byte b[] = new byte[1024 * 1024 * 10];
		int i = inputStream.read(b);
		while (i != -1) {
			out.write(b, 0, i);
			i = inputStream.read(b);
		}
		out.close();
		inputStream.close();

		// 灏嗘枃浠跺悕鏀惧叆head
		stu.setHead(fileName);

		studentService.update(stu);
		return stu;
	}





	//瑕嗙洊娌′慨鏀圭殑鍊�
	@ModelAttribute
	public void get(Student student, Map<String, Object> map) {
		if (student.getId() != null) {
			map.put("student", studentService.getStudentById(student.getId()));
		}
	}

	/**
	 * 鏂囦欢涓嬭浇 鏀寔澶ф枃浠朵笅杞�
	 *
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String downloadResource(HttpSession session, HttpServletRequest request, HttpServletResponse response,
								   @RequestParam("fileName") String fileName) {


		// 鑾峰彇椤圭洰璺緞 寰楀埌鏂囦欢鎵�鍦ㄤ綅缃�
		String realPath = "E:\\eclipse-4.11\\spring\\SpringCrud\\WebContent\\files\\" + fileName;
		if (fileName != null && !fileName.equals("")) {
			File file = new File(realPath);
			response.setContentType("application/octet-stream");
			// 璁剧疆
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			byte[] buffer = new byte[1024 * 1024 * 2];
			FileInputStream in = null;// 杈撳叆娴�
			BufferedInputStream out = null;// 杈撳嚭娴�
			try {
				// 灏嗚鏂囦欢鍔犲叆鍒拌緭鍏ユ祦涔嬩腑
				in = new FileInputStream(file);

				// 灏嗚緭鍏ヨ浆涓鸿緭鍑烘祦
				out = new BufferedInputStream(in);
				OutputStream os = response.getOutputStream();
				int i = out.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = out.read(buffer);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 鏂囦欢涓婁紶
	 *
	 * @param desc
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/FileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile[] file,
								 HttpSession session) throws IOException {
		// 鑾峰彇ServletContext鐨勫璞� 浠ｈ〃褰撳墠WEB搴旂敤

		// 寰楀埌鏂囦欢涓婁紶鐩殑浣嶇疆鐨勭湡瀹炶矾寰�
		// String path = session.getServletContext().getRealPath("");//鑾峰彇椤圭洰鍔ㄦ�佺粷瀵硅矾寰�
		for (MultipartFile multipartFile : file) {
			String prefix = UUID.randomUUID().toString();
			prefix = prefix.replace("-", "");
			String fileName = prefix + "_" + multipartFile.getOriginalFilename();// 浣跨敤UUID鍔犲墠缂�鍛藉悕鏂囦欢锛岄槻姝㈠悕瀛楅噸澶嶈瑕嗙洊
			// 杈撳叆娴侊紝涓婁紶鏂囦欢鐨�
			InputStream inputStream = multipartFile.getInputStream();

			// 杈撳嚭娴�,淇濆瓨鏂囦欢鐨�
			OutputStream out = new FileOutputStream(
					new File("E:\\eclipse-4.11\\spring\\SpringCrud\\WebContent\\files\\" + fileName));

			byte b[] = new byte[1024 * 1024 * 10];
			int i = inputStream.read(b);
			while (i != -1) {
				out.write(b, 0, i);
				i = inputStream.read(b);

				if (i == -1) {
					out.close();
					inputStream.close();
				}
			}
		}

		return "success";
	}
}
