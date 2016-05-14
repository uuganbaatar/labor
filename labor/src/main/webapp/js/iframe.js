var $originalForm = $("form"),
    $file = $originalForm.find("input:file"),
    $target = $("<iframe>", {
      name:"upload_frame"
    }).hide().appendTo("body"),
    $form = $("<form>", {
      target:$target.attr("name"),
      method:"post",
      enctype:"multipart/form-data",
      encoding:"multipart/form-data",
      action:$originalForm.attr("action")
    }).hide().appendTo("body"),
    data = $originalForm.serializeArray();
 
// clone file inputs and attach original to newly created form
$file.after(function() {
  return this.clone();
}).appendTo($form);
 
// create hidden inputs for all data entries and attach them to form
$.each(data, function () {
  $("<input>", {
    type:"hidden",
    name:this.name,
    value:this.value
  }).appendTo($form);
});
 
// submit form
$form.on("submit",function () {
  $target.on("load", function () {
    // when iframe is loaded cache its contents and remove it
    var content = $target.contents().text(),
        response = $.parseJSON(content);
    $form.remove();
    $target.remove();
    // do something with response data
  });
}).submit();