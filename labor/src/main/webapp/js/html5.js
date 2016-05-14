$.ajax({
  url:self.attr("action"),
  type:"post",
  data:new FormData(self.get(0)),
  // options to tell jquery not to process data or worry about content-type
  cache:false,
  contentType:false,
  processData:false
});