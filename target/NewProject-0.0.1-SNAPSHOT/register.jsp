<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
    integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
    crossorigin="anonymous">
</head>
<body>
<section class="vh-100" style="background-color: #F0EFEF;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-8">
        <div class="card" style="border-radius: 1rem;">
          <div class="card-body p-4 p-lg-5 text-black">

            <form method="post" action="Regis">

              <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Create your account</h5>

              <div class="form-outline mb-4">
                <input type="text" id="username" name="username" class="form-control form-control-lg" />
                <label class="form-label" for="username">Username</label>
              </div>

              <div class="form-outline mb-4">
                <input type="text" id="login" name="login" class="form-control form-control-lg" />
                <label class="form-label" for="login">Login</label>
              </div>

              <div class="form-outline mb-4">
                <input type="password" id="password" name="password" class="form-control form-control-lg" />
                <label class="form-label" for="password">Password</label>
              </div>

              
              <div class="form-outline mb-4">
                <select class="form-control" id="role" name="role">
                  <option value="admin">Admin</option>
                  <option value="user">User</option>
                </select>
                <label class="form-label" for="role">Role</label>
              </div>
               

              <button class="btn btn-dark btn-lg btn-block" type="submit" >Register</button>

            </form>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
