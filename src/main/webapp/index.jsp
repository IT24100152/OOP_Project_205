<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard | BikeShare Pro</title>
  <link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="admin.css">
</head>
<body>
<!-- Sidebar Navigation -->
<div class="sidebar">
  <div class="sidebar-brand">
    <div class="brand-flex">
      <img src="${pageContext.request.contextPath}/resources/images/logo.png" width="40px" alt="BikeShare Logo">
      <div class="brand-name">
        <span class="brand-highlight">Bike</span>Share
      </div>
    </div>
    <div class="toggle-btn">
      <i class="fas fa-bars"></i>
    </div>
  </div>

  <div class="sidebar-menu">
    <div class="menu-item active">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-tachometer-alt"></i></span>
        <span class="menu-text">Dashboard</span>
      </a>
    </div>
    <div class="menu-item">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-users"></i></span>
        <span class="menu-text">Users</span>
        <span class="badge badge-pill badge-primary">24</span>
      </a>
    </div>
    <div class="menu-item">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-bicycle"></i></span>
        <span class="menu-text">Bikes</span>
        <span class="badge badge-pill badge-success">56</span>
      </a>
    </div>
    <div class="menu-item">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-exchange-alt"></i></span>
        <span class="menu-text">Rentals</span>
      </a>
    </div>
    <div class="menu-item">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-chart-line"></i></span>
        <span class="menu-text">Analytics</span>
      </a>
    </div>
    <div class="menu-item">
      <a href="#">
        <span class="menu-icon"><i class="fas fa-cog"></i></span>
        <span class="menu-text">Settings</span>
      </a>
    </div>
  </div>

  <div class="sidebar-footer">
    <div class="user-profile">
      <img src="${pageContext.request.contextPath}/resources/images/admin-avatar.jpg" alt="Admin">
      <div class="user-info">
        <div class="user-name">Osanda Nimalarathna</div>
        <div class="user-role">Super Admin</div>
      </div>
      <div class="user-logout">
        <a href="#"><i class="fas fa-sign-out-alt"></i></a>
      </div>
    </div>
  </div>
</div>

<!-- Main Content -->
<div class="main-content">
  <header>
    <h1 class="page-title">Dashboard Overview</h1>
    <div class="header-actions">
      <div class="search-box">
        <input type="text" placeholder="Search...">
        <i class="fas fa-search"></i>
      </div>
      <div class="notifications">
        <i class="fas fa-bell"></i>
        <span class="badge">3</span>
      </div>
    </div>
  </header>

  <main>
    <!-- Stats Cards -->
    <div class="stats-grid">
      <div class="stat-card bg-gradient-primary">
        <div class="stat-info">
          <div class="stat-title">Total Users</div>
          <div class="stat-value">1,284</div>
          <div class="stat-change positive">
            <i class="fas fa-arrow-up"></i> 12.5% from last week
          </div>
        </div>
        <div class="stat-icon">
          <i class="fas fa-users"></i>
        </div>
      </div>

      <div class="stat-card bg-gradient-success">
        <div class="stat-info">
          <div class="stat-title">Available Bikes</div>
          <div class="stat-value">328</div>
          <div class="stat-change positive">
            <i class="fas fa-arrow-up"></i> 8.2% from last week
          </div>
        </div>
        <div class="stat-icon">
          <i class="fas fa-bicycle"></i>
        </div>
      </div>

      <div class="stat-card bg-gradient-info">
        <div class="stat-info">
          <div class="stat-title">Active Rentals</div>
          <div class="stat-value">156</div>
          <div class="stat-change negative">
            <i class="fas fa-arrow-down"></i> 3.1% from last week
          </div>
        </div>
        <div class="stat-icon">
          <i class="fas fa-exchange-alt"></i>
        </div>
      </div>

      <div class="stat-card bg-gradient-warning">
        <div class="stat-info">
          <div class="stat-title">Revenue Today</div>
          <div class="stat-value">$2,845</div>
          <div class="stat-change positive">
            <i class="fas fa-arrow-up"></i> 24.7% from yesterday
          </div>
        </div>
        <div class="stat-icon">
          <i class="fas fa-dollar-sign"></i>
        </div>
      </div>
    </div>

    <!-- Charts Row -->
    <div class="row">
      <div class="col-lg-8">
        <div class="card">
          <div class="card-header">
            <h3>Rental Activity</h3>
            <div class="card-actions">
              <select class="form-select">
                <option>Last 7 Days</option>
                <option>Last 30 Days</option>
                <option>This Year</option>
              </select>
            </div>
          </div>
          <div class="card-body">
            <div class="chart-container">
              <canvas id="rentalChart"></canvas>
            </div>
          </div>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="card">
          <div class="card-header">
            <h3>Bike Types</h3>
          </div>
          <div class="card-body">
            <div class="chart-container">
              <canvas id="bikeTypeChart"></canvas>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Recent Activity Table -->
    <div class="card">
      <div class="card-header">
        <h3>Recent Activity</h3>
        <button class="btn btn-sm btn-primary">View All</button>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table">
            <thead>
            <tr>
              <th>ID</th>
              <th>User</th>
              <th>Action</th>
              <th>Bike</th>
              <th>Time</th>
              <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>#R-2841</td>
              <td>
                <div class="user-avatar">
                  <img src="${pageContext.request.contextPath}/resources/images/user1.jpg" alt="User">
                  <span>John Smith</span>
                </div>
              </td>
              <td>Bike Rental</td>
              <td>Mountain Bike #B-42</td>
              <td>10:45 AM</td>
              <td><span class="badge bg-success">Active</span></td>
            </tr>
            <tr>
              <td>#R-2840</td>
              <td>
                <div class="user-avatar">
                  <img src="${pageContext.request.contextPath}/resources/images/user2.jpg" alt="User">
                  <span>Sarah Johnson</span>
                </div>
              </td>
              <td>Bike Return</td>
              <td>City Bike #B-18</td>
              <td>10:30 AM</td>
              <td><span class="badge bg-secondary">Completed</span></td>
            </tr>
            <tr>
              <td>#R-2839</td>
              <td>
                <div class="user-avatar">
                  <img src="${pageContext.request.contextPath}/resources/images/user3.jpg" alt="User">
                  <span>Michael Brown</span>
                </div>
              </td>
              <td>Bike Rental</td>
              <td>Electric Bike #B-75</td>
              <td>09:15 AM</td>
              <td><span class="badge bg-success">Active</span></td>
            </tr>
            <tr>
              <td>#R-2838</td>
              <td>
                <div class="user-avatar">
                  <img src="${pageContext.request.contextPath}/resources/images/user4.jpg" alt="User">
                  <span>Emily Davis</span>
                </div>
              </td>
              <td>Bike Reservation</td>
              <td>Road Bike #B-29</td>
              <td>08:45 AM</td>
              <td><span class="badge bg-info">Pending</span></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </main>
</div>

<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
</body>
</html>