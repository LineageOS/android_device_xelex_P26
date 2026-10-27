#
# SPDX-FileCopyrightText: The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

# Inherit from xelex mt6789-common
$(call inherit-product, device/xelex/mt6789-common/common.mk)

# AAPT
PRODUCT_AAPT_CONFIG := normal
PRODUCT_AAPT_PREF_CONFIG := xxhdpi

# Keylayout
PRODUCT_COPY_FILES += \
    $(LOCAL_PATH)/configs/idc/touch_kbd.idc:$(TARGET_COPY_OUT_VENDOR)/usr/idc/touch_kbd.idc

# Overlays
PRODUCT_PACKAGES += \
    FrameworksResDevice \
    SystemUIOverlayTarget

# Soong namespaces
PRODUCT_SOONG_NAMESPACES += \
    $(LOCAL_PATH)

# Inherit the proprietary files
$(call inherit-product, vendor/xelex/P26/P26-vendor.mk)
