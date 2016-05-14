package mn.odi.labor.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.imageio.ImageIO;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.util.Constants;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.got5.tapestry5.jquery.JQueryEventConstants;
import org.got5.tapestry5.jquery.components.AjaxUpload;

import se.unbound.tapestry.breadcrumbs.BreadCrumb;
import se.unbound.tapestry.breadcrumbs.BreadCrumbReset;

@BreadCrumb(titleKey = "userProfile")
@BreadCrumbReset(ignorePages = { Object.class })
@Import(stylesheet = { "context:css/home.css","context:css/menu.css",
		"context:css/jquery-ui.css", }, library = { "context:js/custom.js" })
public class UserProfile {

	@SessionState
	private LoginState loginState;

	/*
	 * INJECTS
	 */

	@Inject
	private SccDAO sccDAO;

	@Inject
	private Messages messages;

	@Inject
	private AlertManager manager;

	@Inject
	private Request request;

	@Inject
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private Context context;

	@Inject
	private AssetSource imageUrl;

	@Inject
	private ComponentResources resources;

	@InjectComponent
	private Zone uploadResult;

	/*
	 * PROPERTIES
	 */

	@Property
	@Persist
	private UploadedFile uploadedFile;

	@Property
	@Persist
	private User _user;



	/*
	 * PAGE RENDER
	 */
	void setupRender() {
		if (_user == null) {
			_user = (User) sccDAO.getObject(User.class, loginState
					.getUser().getId());
		}
	}

	/*
	 * EVENTS
	 */



	@OnEvent(component = "uploadImage", value = JQueryEventConstants.AJAX_UPLOAD)
	void onImageUpload(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;

		writePicture();

		ajaxResponseRenderer.addRender("uploadResult", uploadResult);
	}

	@OnEvent(component = "uploadImage", value = JQueryEventConstants.NON_XHR_UPLOAD)
	Object onNonXHRImageUpload(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;

		writePicture();

		final JSONObject result = new JSONObject();

		final JSONObject params = new JSONObject().put(
				"url",
				resources.createEventLink("myCustomEvent", "NON_XHR_UPLOAD")
						.toURI()).put("zoneId", "uploadResult");

		result.put(AjaxUpload.UPDATE_ZONE_CALLBACK, params);

		return result;
	}

	@OnEvent(value = "myCustomEvent")
	void onMyCustomEvent(final String someParam) {
		ajaxResponseRenderer.addRender("uploadResult", uploadResult);
	}

	/*
	 * METHODS
	 */
	@CommitAfter
	private void writePicture() {
		if (uploadedFile != null) {
			byte[] bFile = new byte[(int) uploadedFile.getSize()];

			try {
				InputStream inputStream = (InputStream) uploadedFile
						.getStream();

				inputStream.read(bFile);

				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			_user.setPictureName(_user.getUuid()
					+ uploadedFile.getFileName());

			_user.setPictureSource(bFile);

			sccDAO.updateObject(_user);

			String tmpImg = Constants.TMP_IMAGE_PATH;

			tmpImg = tmpImg.concat(_user.getPictureName());

			String path = context.getRealFile(tmpImg).getPath();

			File tmpFile = new File(path);

			uploadedFile.write(tmpFile);
		}
	}

	public String getLastAccessDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

		return (_user.getLastAccessDate() == null) ? "----.--.-- --:--"
				: dateFormat.format(_user.getLastAccessDate());
	}

	public String getPasswordChangeCss() {
		return (_user.getId() != null) ? "displayShow" : "displayHide";
	}

	public Asset getImageUrl() {
		String img = "context:";
		String unknownImg = "/images/unknown-profile.png";

		if (uploadedFile != null) {

			try {
				img = img.concat(Constants.TMP_IMAGE_PATH);
				img = img.concat(_user.getPictureName());
			} catch (Exception ex) {
				img = img.concat(unknownImg);
			}

			return imageUrl.getAsset(null, img, Locale.ENGLISH);
		}

		if (_user.getPictureSource() == null
				|| _user.getPictureName() == null) {

			img = img.concat(unknownImg);

			return imageUrl.getAsset(null, img, Locale.ENGLISH);
		}

		try {
			byte[] bAvatar = _user.getPictureSource();

			InputStream in = new ByteArrayInputStream(bAvatar);

			try {
				BufferedImage bufferedImage = ImageIO.read(in);

				String tmpImg = Constants.TMP_IMAGE_PATH;

				tmpImg = tmpImg.concat(_user.getPictureName());

				String path = context.getRealFile(tmpImg).getPath();

				File outputfile = new File(path);

				ImageIO.write(bufferedImage, Constants.IMAGETYPE, outputfile);
			} catch (IOException e) {
				e.printStackTrace();

				img = img.concat(unknownImg);

				return imageUrl.getAsset(null, img, Locale.ENGLISH);

			}

			img = img.concat(Constants.TMP_IMAGE_PATH);
			img = img.concat(_user.getPictureName());

			return imageUrl.getAsset(null, img, Locale.ENGLISH);

		} catch (Exception e) {
			img = img.concat(unknownImg);

			return imageUrl.getAsset(null, img, Locale.ENGLISH);
		}
	}

	public String getBoUserIsActive() {
		String isActive = String.valueOf(_user.isEnabled());

		return messages.get(isActive);
	}
}