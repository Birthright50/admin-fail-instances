# admin-fail-instances
1) Run discovery server
2) Run config server
3) Run admin server
4) Run cart service.

Expected: Admin service shows in UI config-server, admin-server and cart-service.
Actual: Admin service shows only config-server.

When we reboot admin-server (but cart-service still alive), we can see in UI cart-service and config-service, but no admin-server.
